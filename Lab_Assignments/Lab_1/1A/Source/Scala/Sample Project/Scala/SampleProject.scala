import java.io._

object SampleProject {
  def main(args: Array[String]) {
    val Text = new PrintWriter(new File("SampleScala.txt" ))

    Text.write("1.\tCreating a Scala project \nIn IntelliJ using Scala and SBT. Add any library to SBT and use in it in your project.\n")
    Text.close()
  }
}