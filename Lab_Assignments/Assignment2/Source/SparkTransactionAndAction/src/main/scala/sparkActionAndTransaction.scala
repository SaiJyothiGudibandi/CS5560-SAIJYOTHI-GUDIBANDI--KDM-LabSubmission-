import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by saijo on 6/20/2017.
  */
object sparkActionAndTransaction {
  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\Users\\saijy\\Documents\\HADOOP");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc=new SparkContext(sparkConf)


    val input=sc.textFile("InputFile\\cypriot.financial.crisis.36.txt")
    val input1=sc.textFile("InputFile\\cypriot.financial.crisis.38.txt");
    val input2=sc.textFile("InputFile\\cypriot.financial.crisis.40.txt");
    val unionSet = input1.union(input2);
    val intersectionSet = input1.intersection(input2);
    val distinctSet = input1.distinct(2);
    var KeyArr = new Array[String](50)
    KeyArr = unionSet.take(30)
    unionSet.saveAsTextFile("Output/unionOFWords");
    println(unionSet.count())

    //Displaying all the contents from both files
    println("Collection of all words")
    for(stringUnion <- KeyArr){
      println(stringUnion)
    }

    var KeyArr1 = new Array[String](50)
    KeyArr1 = intersectionSet.take(30)
    intersectionSet.saveAsTextFile("Output/intersectionWords");
    println(intersectionSet.count())
    println("Intersection of words between the file")
    for(stringInter <- KeyArr1){
      println(stringInter)
    }

    var KeyArr2 = new Array[String](50)
    KeyArr2 = distinctSet.take(30)
    distinctSet.saveAsTextFile("Output/distinctWords");
    println(distinctSet.count())
    println("Distinct of words between the files")
    for(stringDistinct <- KeyArr2){
      println(stringDistinct)
    }
  }

}
