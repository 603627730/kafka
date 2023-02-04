package com.example.producer.config;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

//@Configuration
public class KafkaConfig implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
//        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
//        return ThreadLocalRandom.current().nextInt(partitions.size());
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        return Math.abs(key.hashCode()) % partitions.size();
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
