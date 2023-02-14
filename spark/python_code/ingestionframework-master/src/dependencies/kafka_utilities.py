# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""
from __future__ import print_function
from pyspark.streaming.kafka import KafkaUtils
from pyspark.streaming import StreamingContext


from config.config_objects import KafkaInput


def kafka_utils(streaming_context:StreamingContext, kafka_input):
    print(kafka_input)
    #kafka_input = KafkaInput(kafka_input)

    # kafka_stream = KafkaUtils.createStream(
    #         ssc= streaming_context,
    #         zkQuorum = kafka_input.server,
    #         groupId = kafka_input.consumerGroup,
    #         topics= {"Item_Pricing_Topic":12},
    #         kafkaParams=None
    #     )


    # kafka_stream = KafkaUtils.createDirectStream(
    #     ssc=streaming_context,
    #     topics = ["Item_Pricing_Topic"],
    #     kafkaParams= {
    #         "metadata.broker.list": "10.7.27.4:31091"        },
    # )

    kafka_stream = KafkaUtils.createStream(
        ssc=streaming_context,
        zkQuorum=kafka_input.server,
        groupId=kafka_input.consumerGroup,
        topics={"Item_Pricing_Topic": 12},
        kafkaParams=None
    )

    def message_handler(message):
        records = message.collect()
        for record in records:
            record.pprint()

    kafka_stream.foreachRDD(lambda x: message_handler(x))

