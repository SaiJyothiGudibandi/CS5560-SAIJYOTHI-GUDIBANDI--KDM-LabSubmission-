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
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static edu.stanford.nlp.util.logging.RedwoodConfiguration.Handlers.output;


public class Main {
    public static void main(String args[]) throws IOException {
        Properties ps = new Properties();
        ps.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pl = new StanfordCoreNLP(ps);
        String data = readText("src/main/Data/dataset.txt");
        Annotation document = new Annotation(data);
        pl.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                String lemmatize = token.get(CoreAnnotations.LemmaAnnotation.class);
                System.out.println(lemmatize);
            }
        }
    }

    public static String readText(String fileName) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder str= new StringBuilder();
            String l = bf.readLine();

            while (l != null) {
                str.append(l);
                l = bf.readLine();
            }
            return str.toString();
        } finally {
            bf.close();
        }
    }


}
