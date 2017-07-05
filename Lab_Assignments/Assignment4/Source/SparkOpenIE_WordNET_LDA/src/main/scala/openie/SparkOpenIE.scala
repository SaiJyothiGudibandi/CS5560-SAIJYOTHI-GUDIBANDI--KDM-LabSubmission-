package openie

import java.io.{BufferedWriter, FileOutputStream, OutputStreamWriter}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object SparkOpenIE {

  def main(args: Array[String]) {
    // Configuration
    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)


    // Turn off Info Logger for Console
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val fileWriteSuji = "Output/OI_triplet.txt"
    val Filewriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileWriteSuji)))

    val input = sc.textFile("data/cypriot.financial.crisis.36.txt").map(line => {
      //Getting OpenIE Form of the word using lda.CoreNLP

      val t=CoreNLP.returnTriplets(line)
      //print(t)
      t
    })

    //println(CoreNLP.returnTriplets("The dog has a lifespan of upto 10-12 years."))
   // println(input.collect().mkString("\n"))

    //input.collect().mkString("\n")

    println(input.collect().mkString("\n"))
    val output=input.collect().mkString;
    Filewriter.write(output);
    Filewriter.close();

  }

}
