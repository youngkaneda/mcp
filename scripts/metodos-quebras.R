library(ggplot2)
setwd("/home/shotaro/myProjects/TCC/pesquisa/scripts/")
csv_data<-read.csv("inputs/metodos-quebras.csv",header=T, sep = ",")
data<-data.frame(methods<-csv_data$methods, breaks<-csv_data$breaks.count)
ggplot(data, aes(x=methods, y=breaks)) + 
  geom_bar(stat="identity", fill="#293749", width=0.4) +
  labs(x="métodos", y="quebras") +
  theme(axis.text.x=element_text(angle=90, hjust=1))