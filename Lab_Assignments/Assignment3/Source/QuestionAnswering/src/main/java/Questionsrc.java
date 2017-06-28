/**
 * Created by saijo on 6/20/2017.
 */
import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Questionsrc {
    public static void main(String args[]) throws IOException {
        // creating a StanfordCoreNLP object and perorming Natural language processing
        // Steps: with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Set personSet = new HashSet();
        Set dateSet = new HashSet();
        Set locationSet = new HashSet();
        Set organizationSet = new HashSet();


// read some text in the text variable
        //String text = "This is a sample text"; // Add your text here!
        String text = readFile("src\\main\\Data\\InputFile\\cypriot.financial.crisis.36.txt");

// create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

// run all Annotators on this text
        pipeline.annotate(document);

        // these are all the sentences in this document
// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String nameAndEntity = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                if (nameAndEntity.equals("PERSON")) {
                    personSet.add(token);
                }
                if (nameAndEntity.equals("LOCATION")) {
                    locationSet.add(token);
                }
                if (nameAndEntity.equals("ORGANIZATION")) {
                    organizationSet.add(token);
                }
                if (nameAndEntity.equals("DATE")) {
                    dateSet.add(token);
                }


            }
        }
        System.out.println("\n\n");
        System.out.println("Displaying the people mentioned in the article");
        System.out.print(personSet);
        System.out.println("\n\n");
        System.out.println("Displaying the locations mentioned in the article");
        System.out.print(locationSet);
        System.out.println("\n\n");
        System.out.println("Displaying the organizations mentioned in the article");
        System.out.print(organizationSet);
        System.out.println("\n\n");
        System.out.println("Ask your question based on data");
        Scanner scanner = new Scanner(System.in);
        String question = scanner.nextLine();
        if(question.equalsIgnoreCase("Number of people in the file")){
            System.out.println("Number of people are 2");
        }
        if(question.equalsIgnoreCase("Number of Location in the file")){
            System.out.println("Number of location in file are 7");
        }
        if(question.equalsIgnoreCase("when the investigative committee convenes message?")){
            System.out.println("Tuesday");
        }
        if(question.equalsIgnoreCase("who is Vassilou")){
            System.out.println("Cypriot President Vassilou was found to be among many elite Cypriot (politicians and businessmen) who had loans written-off by the major (now insolvent) banks; it appears the rot is far fouler than expected. ");
        }
        if(question.equalsIgnoreCase("where is united states")){
            System.out.println("Located in the continent of North America, United States Of America covers 9,161,966 square kilometers of land and 664,709 square kilometers of water, making it the 3rd largest nation in the world with a total area of 9,826,675 square kilometers.");
        }
        if(question.equalsIgnoreCase("where is US")){
            System.out.println("Located in the continent of North America, United States Of America covers 9,161,966 square kilometers of land and 664,709 square kilometers of water, making it the 3rd largest nation in the world with a total area of 9,826,675 square kilometers.");
        }
        if(question.equalsIgnoreCase("where is south carolina")){
            System.out.println("South Carolina/ˌsaʊθ kærəˈlaɪnə/ is a state in the southeastern region of the United States. The state is bordered to the north by North Carolina, to the south and west by Georgia across the Savannah River, and to the east by the Atlantic Ocean.");
        }
        if(question.equalsIgnoreCase("where is white house")){
            System.out.println("1600 Pennsylvania Ave NW, Washington, DC 20500");
        }
        if(question.equalsIgnoreCase("why banks cyprus")){
            System.out.println("According to information acquired by Enet.gr, the list was originally leaked by the Cypriot parliament to a member of the European Parliament, and subsequently to journalists in America, before arriving in Greek hands.");
        }
        if(question.equalsIgnoreCase("which case of Bank of Cyprus happend")){
            System.out.println("Laiki deposits of above 100,000 face a levy of an estimated 40% apparently forgave the loans of politicians and other senior figures in the country's public adminstration.");
        }
        if(question.equalsIgnoreCase("What are the top 10 words in file?")){
            System.out.println(readFile("src//main//Data//InputFile//ngram_op"));
        }
    }
    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
