#! /usr/bin/env bash
#
# Pushes jackson-mapper-asl to the cdh repo.
# Inspired by http://github.mtv.cloudera.com/CDH/cdh/blob/cdh6.x/bin/maven-deploy-to-artifactory.sh

# Catch errors
set -ex
trap 'echo Error in $0 at line $LINENO: $(cd "'$PWD'" && awk "NR == $LINENO" $0)' ERR

PWD=$(dirname $0)

VERSION=$(cat ${PWD}/../build/VERSION.txt)
ARTDIR=
if echo "${VERSION}" | grep -q SNAPSHOT; then
    ARTDIR=cdh-snapshot-local
else
    ARTDIR=cdh-staging-local
fi

MVNDIR=org/codehaus/jackson/jackson-mapper-asl/${VERSION}
REMOTE_REPO=http://maven.jenkins.cloudera.com:8081/artifactory/${ARTDIR}
DIR="${HOME}/.m2/repository/$MVNDIR"

[ -d "${DIR}" ] || exit 1
set +x
[ "${REPOUSERNAME}" ] || exit 1
[ "${REPOPASSWORD}" ] || exit 1
set -x 

which md5sum || exit $?
which sha1sum || exit $?

find "${DIR}" -regex '.*\.\(jar\|pom\)' -type f -print0 | while IFS= read -d '' f; do
  echo "Processing: $f"

  md5Value=$(md5sum "$f" | awk '{print $1}')
  sha1Value=$(sha1sum "$f" | awk '{print $1}')
  bn=$(basename $f)
  ufn="${MVNDIR}/${bn}"

  echo "curl -X PUT .... file: $f  url: ${REMOTE_REPO}/${ufn}"
  # Publish
  set +x
  curl -s -X PUT \
    -u "$REPOUSERNAME:$REPOPASSWORD" \
    -T "$f" \
    -H "X-Checksum-Md5: $md5Value" \
    -H "X-Checksum-Sha1: $sha1Value" \
    "${REMOTE_REPO}/${ufn}"
  set -x
done
