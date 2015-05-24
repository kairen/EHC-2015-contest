#!/usr/bin/env bash

sysctl -w vm.drop_caches=3
team="Team12"

function pre_processing() {
  LC_ALL=C grep -o "act=order;uid=[a-zA-Z0-9]*\>;plist=[0-9,]*\>" EHC_1st_round.log > mine_data.csv
  hadoop fs -put mine_data.csv "/tmp/$team/"
}

# Decompress Log File
LC_ALL=C tar xf /data/EHC_1st.tar.gz

#HDFS Process
hadoop fs -mkdir "/tmp/$team"

# Data pre-process and upload to HDFS
pre_processing

# Run Hadoop MapReduce Jar
hadoop jar ETUContest.jar "/tmp/$team/mine_data.csv" "/tmp/$team/output"

# Get Hadoop Result Data from HDFS
hadoop fs -cat "/tmp/$team/output/part-r-00000" > mr_result.txt

# R Analytics and Statistics
Rscript analytics_top.R

hadoop fs -put EHC_1st_round.log  "/tmp/$team/EHC_1st_round.log"