1、下载seata 1.0.0
2、修改registry.conf 配置

registry {
# file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
type = "nacos"

nacos {
serverAddr = "192.168.6.111:8848,192.168.6.112:8848,192.168.6.113:8848"
namespace = ""
cluster = "default"
}
}

config {
# file、nacos 、apollo、zk、consul、etcd3
type = "nacos"

nacos {
serverAddr = "192.168.6.111:8848,192.168.6.112:8848,192.168.6.113:8848"
namespace = ""
group = "SEATA_GROUP"
username = ""
password = ""
}
}
3、上传seata配置到nacos
nacos_config.txt

内容
transport.type=TCP
transport.server=NIO
transport.heartbeat=true
transport.enable-client-batch-send-request=false
transport.thread-factory.boss-thread-prefix=NettyBoss
transport.thread-factory.worker-thread-prefix=NettyServerNIOWorker
transport.thread-factory.server-executor-thread-prefix=NettyServerBizHandler
transport.thread-factory.share-boss-worker=false
transport.thread-factory.client-selector-thread-prefix=NettyClientSelector
transport.thread-factory.client-selector-thread-size=1
transport.thread-factory.client-worker-thread-prefix=NettyClientWorkerThread
transport.thread-factory.boss-thread-size=1
transport.thread-factory.worker-thread-size=8
transport.shutdown.wait=3
service.vgroup_mapping.sample-account-service=default
service.vgroup_mapping.sample-order-service=default
service.vgroup_mapping.sample-repo-service=efault
service.default.grouplist=127.0.0.1:8091
service.enableDegrade=false
service.disableGlobalTransaction=false
client.rm.async.commit.buffer.limit=10000
client.rm.lock.retry.internal=10
client.rm.lock.retry.times=30
client.rm.report.retry.count=5
client.rm.lock.retry.policy.branch-rollback-on-conflict=true
client.rm.table.meta.check.enable=false
client.rm.report.success.enable=true
client.tm.commit.retry.count=5
client.tm.rollback.retry.count=5
store.mode=db
store.file.dir=file_store/data
store.file.max-branch-session-size=16384
store.file.max-global-session-size=512
store.file.file-write-buffer-cache-size=16384
store.file.flush-disk-mode=async
store.file.session.reload.read_size=100
store.db.datasource=dbcp
store.db.db-type=mysql
store.db.driver-class-name=com.mysql.jdbc.Driver
store.db.url=jdbc:mysql://127.0.0.1:3306/seata?useUnicode=true
store.db.user=mysql
store.db.password=mysql
store.db.min-conn=1
store.db.max-conn=3
store.db.global.table=global_table
store.db.branch.table=branch_table
store.db.query-limit=100
store.db.lock-table=lock_table
server.recovery.committing-retry-period=1000
server.recovery.asyn-committing-retry-period=1000
server.recovery.rollbacking-retry-period=1000
server.recovery.timeout-retry-period=1000
server.max.commit.retry.timeout=-1
server.max.rollback.retry.timeout=-1
client.undo.data.validation=true
client.undo.log.serialization=jackson
server.undo.log.save.days=7
server.undo.log.delete.period=86400000
client.undo.log.table=undo_log
client.log.exceptionRate=100
transport.serialization=seata
transport.compressor=none
metrics.enabled=false
metrics.registry-type=compact
metrics.exporter-list=prometheus
metrics.exporter-prometheus-port=9898
client.support.spring.datasource.autoproxy=false

4、执行脚本nacos_config.sh
内容如下：
#!/usr/bin/env bash
# Copyright 1999-2019 Seata.io Group.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at、
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

if [[ $# != 1 ]]; then
echo "USAGE: $0 nacosAddr"
exit 1
fi

nacosAddr=$1
echo "set nacosAddr=$nacosAddr"
contentType="content-type:application/json;charset=UTF-8"

failCount=0
#tempLog=$(mktemp -t nacos-config.log)
function addConfig() {
curl -X POST -H ${1} "http://$2/nacos/v1/cs/configs?dataId=$3&group=SEATA_GROUP&content=$4" >> nacos-config.log
#if [[ -z $(cat ${tempLog}) ]]; then
#  echo "\033[31m Please check the cluster status. \033[0m"
#  exit 1
#fi
#if [[ $(cat ${tempLog}) =~ "true" ]]; then
#  echo "Set $3=$4\033[32m successfully \033[0m"
#else
#  echo "Set $3=$4\033[31m failure \033[0m"
#  (( failCount++ ))
#fi
}

count=0
for line in $(cat $(dirname "$PWD")/config.txt); do
(( count++ ))
key=${line%%=*}
value=${line#*=}
addConfig ${contentType} ${nacosAddr} ${key} ${value}
done

echo "========================================================================="
echo " Complete initialization parameters, \033[32m total-count:$count \033[0m, \033[31m failure-count:$failCount \033[0m"
echo "========================================================================="

if [[ ${failCount} -eq 0 ]]; then
echo "\033[32m Init nacos config finished, please start seata-server. \033[0m"
else
echo "\033[31m init nacos config fail. \033[0m"
fi

5、遇到问题
store.mode=db   造成找不到service
nacos 服务列表里面， 存在服务造成dubbo服务注册不上， 删除nacos服务列表中的服务即可


