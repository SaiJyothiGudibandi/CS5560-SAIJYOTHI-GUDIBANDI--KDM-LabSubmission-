package wordnet

import java.io.{BufferedWriter, FileOutputStream, OutputStreamWriter}

import org.apache.spark.{SparkConf, SparkContext}
import rita.RiWordNet

object WordNetSpark {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C://Users//saijy//Documents//HADOOP")
    val conf = new SparkConf().setAppName("WordNetSpark").setMaster("local[*]").set("spark.driver.memory", "4g").set("spark.executor.memory", "4g")
    val sc = new SparkContext(conf)
    val fileWriteSuji = "Output/WordNet_loan.txt"
    val Filewriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileWriteSuji)))


    val input=sc.textFile("data/cypriot.financial.crisis.36.txt")

    val dd=input.map(f=>{
      val wordnet = new RiWordNet("C://Users//saijy//Documents//WordNet-3.0")
      val farr=f.split(" ")
      getSynoymns(wordnet,"loan")
    })
    println(input.collect().mkString("\n"))
    val output=input.collect().mkString;
    Filewriter.write(output);
    Filewriter.close();
    dd.take(1).foreach(f=>println(f.mkString(" ")))
  }


  def getSynoymns(wordnet:RiWordNet,word:String): Array[String] ={
    println(word)
    val pos=wordnet.getPos(word)
    println(pos.mkString(" "))
    val syn=wordnet.getAllSynonyms(word, pos(0), 10)
    syn
  }

}
