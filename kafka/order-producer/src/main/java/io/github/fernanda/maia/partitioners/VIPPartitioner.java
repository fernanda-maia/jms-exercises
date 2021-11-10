package io.github.fernanda.maia.partitioners;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class VIPPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int partitionNumber =  7;

        if(key.toString().compareToIgnoreCase("Fernanda") != 0) {
            List<PartitionInfo> infoList = cluster.availablePartitionsForTopic(topic);
            partitionNumber = Math.abs(Utils.murmur2(keyBytes) % infoList.size() - 1);
        }

        // murmur >> hash algorithm
        return partitionNumber;
    }

    @Override
    public void close() {
        // Usado para fechar recursos abertos durante a execução o partition()
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
