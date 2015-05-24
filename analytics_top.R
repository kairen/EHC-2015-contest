#!/usr/bin/env Rscript

rawData = read.table("mr_result.txt", colClasses = c("character", "numeric"))
newData = rawData[rev(order(rawData[,2]))[1:20],]

# If "比賽不要規定要補0" Then "捨去直接print row name"
sequence = c("01", "02", "03", "04", "05", 
             "06", "07", "08", "09", "10",
             "11", "12", "13", "14", "15",
             "16", "17", "18", "19", "20") 
 
data = data.frame(sequence, newData[, 1])

write.table(
    data, 
    "Team12_Result.txt", 
    sep=",", 
    quote = FALSE, 
    row.names = FALSE,
    col.names = FALSE
)
