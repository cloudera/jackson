# Cloudera Modifications

This release contains a backport of all the commits related to
CVE-2017-7525 and future related CVEs.

In essence, it's a backport of the following git hashes:

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
