# KafkaProducer
Simple Spring Kafka Producer for a specific topic format. Used to demo RHPAM Signal REST API

All this app does is exposed a REST operation (/kafka/publish) for a PUT call (I use Postman) that expects some specific headers and then an open body.

This app was only used for testing a different app that pulls events from Kafka and pushes them to a jBPM REST API.

This is also a very minimal Spring Kafka Producer...the bootstrap Kafka was running on a local Oracle VirtualBox VM (ubkafka) that did nothing but run Zookeeper and Kafka.
