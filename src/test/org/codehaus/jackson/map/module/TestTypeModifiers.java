package org.codehaus.jackson.map.module;

import java.io.IOException;
import java.lang.reflect.Type;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.type.TypeModifier;
import org.codehaus.jackson.type.JavaType;

public class TestTypeModifiers extends BaseMapTest
{
    /*
    /**********************************************************
    /* Helper classes
    /**********************************************************
     */

    static class ModifierModule extends SimpleModule
    {
        public ModifierModule() {
            super("test", Version.unknownVersion());
        }

        @Override
        public void setupModule(SetupContext context)
        {
            context.addSerializers(new Serializers.None() {
                @Override
                public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type,
                        BeanDescription beanDesc, BeanProperty property)
                {
                    if (MapMarker.class.isAssignableFrom(type.getRawClass())) {
                        return new MyMapSerializer();
                    }
                    if (CollectionMarker.class.isAssignableFrom(type.getRawClass())) {
                        return new MyCollectionSerializer();
                    }
                    return null;
                }
                
            });
            context.addDeserializers(new SimpleDeserializers() {
            });
        }
    }

    interface MapMarker<K,V> {
        public K getKey();
        public V getValue();
    }
    interface CollectionMarker<V> {
        public V getValue();
    }
    
    static class MyMapLikeType implements MapMarker<String,Integer> {
        public String key;
        public int value;

        public MyMapLikeType() { }
        public MyMapLikeType(String k, int v) {
            key = k;
            value = v;
        }

        public String getKey() { return key; }
        public Integer getValue() { return value; }
    }

    static class MyCollectionLikeType implements CollectionMarker<Integer>
    {
        public int value;

        public MyCollectionLikeType() { }
        public MyCollectionLikeType(int v) {
            value = v;
        }

        public Integer getValue() { return value; }
    }

    static class MyMapSerializer extends JsonSerializer<MapMarker<?,?>>
    {
        @Override
        public void serialize(MapMarker<?,?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeNumberField((String) value.getKey(), ((Number) value.getValue()).intValue());
            jgen.writeEndObject();
        }
    }
    static class MyMapDeserializer extends JsonDeserializer<MapMarker<?,?>>
    {
        @Override
        public MapMarker<?,?> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            if (jp.getCurrentToken() != JsonToken.START_OBJECT) throw new IOException("Wrong token: "+jp.getCurrentToken());
            if (jp.nextToken() != JsonToken.FIELD_NAME) throw new IOException("Wrong token: "+jp.getCurrentToken());
            String key = jp.getCurrentName();
            if (jp.nextToken() != JsonToken.VALUE_NUMBER_INT) throw new IOException("Wrong token: "+jp.getCurrentToken());
            int value = jp.getIntValue();
            if (jp.nextToken() != JsonToken.END_OBJECT) throw new IOException("Wrong token: "+jp.getCurrentToken());
            return new MyMapLikeType(key, value);
        }        
    }

    static class MyCollectionSerializer extends JsonSerializer<MyCollectionLikeType>
    {
        @Override
        public void serialize(MyCollectionLikeType value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartArray();
            jgen.writeNumber(value.value);
            jgen.writeEndArray();
        }
    }
    static class MyCollectionDeserializer extends JsonDeserializer<MyCollectionLikeType>
    {
        @Override
        public MyCollectionLikeType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            if (jp.getCurrentToken() != JsonToken.START_ARRAY) throw new IOException("Wrong token: "+jp.getCurrentToken());
            if (jp.nextToken() != JsonToken.VALUE_NUMBER_INT) throw new IOException("Wrong token: "+jp.getCurrentToken());
            int value = jp.getIntValue();
            if (jp.nextToken() != JsonToken.END_ARRAY) throw new IOException("Wrong token: "+jp.getCurrentToken());
            return new MyCollectionLikeType(value);
        }        
    }
    
    static class MyTypeModifier extends TypeModifier
    {
        @Override
        public JavaType modifyType(JavaType type, Type jdkType, TypeBindings context, TypeFactory typeFactory)
        {
            Class<?> raw = type.getRawClass();
            if (MapMarker.class.isAssignableFrom(raw)) {
                JavaType[] params = typeFactory.findTypeParameters(type, MapMarker.class);
                return typeFactory.constructMapLikeType(raw, params[0], params[1]);
            }
            if (CollectionMarker.class.isAssignableFrom(raw)) {
                JavaType[] params = typeFactory.findTypeParameters(type, CollectionMarker.class);
                return typeFactory.constructCollectionLikeType(raw, params[0]);
            }
            return type;
        }
    }
    
    /*
    /**********************************************************
    /* Unit tests
    /**********************************************************
     */

    /**
     * Basic test for ensuring that we can get "xxx-like" types recognized.
     */
    public void testLikeTypeConstruction() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTypeFactory(mapper.getTypeFactory().withModifier(new MyTypeModifier()));
        JavaType type = mapper.constructType(MyMapLikeType.class);
        assertTrue(type.isMapLikeType());
        // also, must have resolved type info
        JavaType param = ((MapLikeType) type).getKeyType();
        assertNotNull(param);
        assertSame(String.class, param.getRawClass());
        param = ((MapLikeType) type).getContentType();
        assertNotNull(param);
        assertSame(Integer.class, param.getRawClass());
        
        type = mapper.constructType(MyCollectionLikeType.class);
        assertTrue(type.isCollectionLikeType());
        param = ((CollectionLikeType) type).getContentType();
        assertNotNull(param);
        assertSame(Integer.class, param.getRawClass());
    }

    public void testCollectionLikeSerialization() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTypeFactory(mapper.getTypeFactory().withModifier(new MyTypeModifier()));
        mapper.registerModule(new ModifierModule());
        assertEquals("[19]", mapper.writeValueAsString(new MyCollectionLikeType(19)));
    }

    public void testMapLikeSerialization() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTypeFactory(mapper.getTypeFactory().withModifier(new MyTypeModifier()));
        mapper.registerModule(new ModifierModule());
        assertEquals("{\"x\":3}", mapper.writeValueAsString(new MyMapLikeType("x", 3)));
    }


    public void testCollectionLikeDeserialization() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTypeFactory(mapper.getTypeFactory().withModifier(new MyTypeModifier()));
        mapper.registerModule(new ModifierModule());
        // !!! TBI
        MyMapLikeType result = mapper.readValue("{\"a\":13}", MyMapLikeType.class);
        assertEquals("a", result.getKey());
        assertEquals(Integer.valueOf(13), result.getValue());
    }

    public void testMapLikeDeserialization() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTypeFactory(mapper.getTypeFactory().withModifier(new MyTypeModifier()));
        mapper.registerModule(new ModifierModule());
        // !!! TBI
        MyCollectionLikeType result = mapper.readValue("[-37]", MyCollectionLikeType.class);
        assertEquals(Integer.valueOf(-37), result.getValue());
    }
}
