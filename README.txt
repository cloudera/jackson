# Cloudera Modifications

This release contains a backport of all the commits related to
jackson-databind CVEs.

The '-cloudera.1' release includes
6ce32ffd18facac6abdbbf559c817b47fcb622c1
60d459cedcf079c6106ae7da2ac562bc32dcabe1
fd8dec2c7fab8b4b4bd60502a0f1d63ec23c24da
f4e8de59a4739273d5692d029f15986bb57dd273
3bfbb835e530055c1941ddf87fde0b08d08dcd38
e8f043d1aac9b82eee907e0f0c3abbdea723a935
ddfddfba6414adbecaff99684ef66eebd3a92e92
02ce588d77761bb03beca5748f3b2c918f205b2a
f031f27a31625d07922bdd090664c69544200a5d
e865a7a4464da63ded9f4b1a2328ad85c9ded78b
2235894210c75f624a3d0cd60bfb0434a20a18bf
978798382ceb72229e5036aa1442943933d6d171
bb45fb16709018842f858f1a6e1118676aaa34bd
755e3bc0cbea30de0102f6a88519a0c34d571bbd
038b471e2efde2e8f96b4e0be958d3e5a1ff1d05
c803a2658e45b8d1095d2504f943bd4ebaab18e9
6799f8f10cc78e9af6d443ed6982d00a13f2e7d2
e66c0a9d3c926ff1b63bf586c824ead1d02f2a3d

All of the backports are mostly a straight copy of the code related to
those hashes referenced. There is only one entry point to this
funtionality: _validateSubType() in BeanDeserializerFactory.java.

This backport was originally performed on the 2.1.0 release and that
has in turn been backported to this older release.

The '-cloudera.2' release includes
27b4defc270454dea6842bd9279f17387eceb737
7487cf7eb14be2f65a1eb108e8629c07ef45e0a1
87d29af25e82a249ea15858e2d4ecbf64091db44
72cd4025a229fb28ec133235003dd4616f70afaa

These commits address CVE-2018-19360, CVE-2018-19361, CVE-2018-19362,
CVE-2018-14718, CVE-2018-14719, CVE-2018-14720, and CVE-2018-14721

The '-cloudera.3' release includes
dda513bd7251b4f32b7b60b1c13740e3b5a43024
c9ef4a10d6f6633cf470d6a469514b68fa2be234
5f7c69bba07a7155adde130d9dee2e54a54f1fa5
ad418eeb974e357f2797aef64aa0e3ffaaa6125b
d4983c740fec7d5576b207a8c30a63d3ea7443de
73c1c2cc76e6cdd7f3a5615cbe3207fe96e4d3db
191a4cdf87b56d2ddddb77edd895ee756b7f75eb
41b7f9b90149e9d44a65a8261a8deedc7186f6af
998efd708284778f29d83d7962a9bd935c228317
9593e16cf5a3d289a9c584f7123639655de9ddac
b5a304a98590b6bb766134f9261e6566dcbbb6d0
fc4214a883dc087070f25da738ef0d49c2f3387e
914e7c9f2cb8ce66724bf26a72adc7e958992497
9f4e97019fb0dd836533d0b6198c88787e235ae2
4d038c9de0aa80a5dae27f552a975cb39cc42b60
9bdc373bcca774ee57b8c42f4af61a7b50f3dc26
82d5d10089d6ac311a41548502b7433016c46fc8
a424c038ba0c0d65e579e22001dec925902ac0ef
592872f4235c7f2a3280725278da55544032f72d
1645efbd392989cf015f459a91c999e59c921b15
05d7e0e13f43e12db6a51726df12c8b4d8040676
e2ba12d5d60715d95105e3e790fc234cfb59893d
113e89fb08b1b6b072d60b3e4737ed407c13db9a
77040d85e3eb6710508e6445640ae1a3d5e60c22
d1c67a0396e84c08d0558fbb843b5bd1f26e1921
5c8642aeae9c756b438ab7637c90ef3c77966e6e
840eae2ca81c597a0010b2126f32dce17d384b70
f6d9c664f6d481703138319f6a0f1fdbddb3a259
6cc9f1a1af323cd156f5668a47e43bab324ae16f
3d97153944f7de9c19c1b3637b33d3cf1fbbe4d7
e701bd852ca9a22e04743104987f11ae575a6fe2
7dbf51bf78d157098074a20bd9da39bd48c18e4a
41b8bdb5ccc1d8edb71acf1c8234da235a24249d
33d96c13fe18a2dad01b19ce195548c9acea9da4
3e8fa3beea49ea62109df9e643c9cb678dabdde1
567194c53ae91f0a14dc27239afb739b1c10448a
1ca0388c2fb37ac6a06f1c188ae89c41e3e15e84
12e23c962ffb4cf1857c5461d72ae54cc8008f29
3ded28aece694d0df39c9f0fa1ff385b14a8656b
4929735be6bdae0d1853d7d269234a30be18408f
261ea429eafedfaf7c784ad96196c3e11c0c928e
2d2f0c736179652ae399360b75f7b523acf46c80
612f971b78c60202e9cd75a299050c8f2d724a59

These commits address CVE-2019-12086, CVE-2019-12384, CVE-2019-12814,
CVE-2019-14379, CVE-2019-14439, CVE-2019-14540, CVE-2019-14892,
CVE-2019-14893, CVE-2019-16335, CVE-2019-16942, CVE-2019-16943,
CVE-2019-17267, CVE-2019-17531, CVE-2019-20330, CVE-2020-8840,
CVE-2020-9546, CVE-2020-9547, CVE-2020-9548, CVE-2020-10969,
CVE-2020-10650, CVE-2020-10672, CVE-2020-10673, CVE-2020-10968,
CVE-2020-11111, CVE-2020-11112, CVE-2020-11113, CVE-2020-11619,
CVE-2020-11620, CVE-2020-14060, CVE-2020-14061, CVE-2020-14062,
CVE-2020-14195, CVE-2020-24750, CVE-2020-24616, CVE-2020-25649,
CVE-2020-35490, CVE-2020-35491, CVE-2020-35728, CVE-2020-36179,
CVE-2020-36180, CVE-2020-36181, CVE-2020-36182, CVE-2020-36183,
CVE-2020-36184, CVE-2020-36185, CVE-2020-36186, CVE-2020-36187,
CVE-2020-36188, CVE-2020-36189, CVE-2021-20190.

== Jackson Json-processor ==

This is the Jackson Json-processor package. Project home page can be found
from:

http://jackson.codehaus.org/

which also has links to support forums such as mailing lists, as well
as links to obtain source code and binary artifacts.

=== Functionality ===

Jackson implements functionality for:

* Reading (parsing) and writing (generation) of Json content
* Data-binding (data-mapping, [de-]serialization) support to
  allow for converting between Json and Java objects
* Support for converting to/from other data formats and access APIs,
  to improve interoperability.

=== Requirements ===

Jackson can be used on standard J2SE 1.5 platform. Some effort is made
to support other non-standard platforms as well.

=== Licensing ===

Jackson can be used for any purpose, but to (re)distribute it,  distributors
(such as libraries and frameworks that use Jackson) will need to
choose which License they want to apply to distribution, and to use appropriate
jars that enclose license documentation.
No work needed beyond choosing the appropriate jar(s).

Currently two Open Source licenses are available for use:

* Apache License 2.0 (AL 2.0)
* Lesser/Library General Public License (LGPL 2.1)

These licenses have proven adequate to cover all current use cases.

=== Contributing ===

All code contributions are to be made according the
Contributor Agreement document found from under DEV/ directory within source
repository and downloadable source archives.

CA is needed to allow Jackson project to publish under different Licenses,
and to indicate good faith effort to comply with copyright rules governing
use and distribution of the source code (and its equivalents as outlined
in the agreement) involved. This is also useful for (re)distributors to
reduce their need to independently verify origin of source code.
