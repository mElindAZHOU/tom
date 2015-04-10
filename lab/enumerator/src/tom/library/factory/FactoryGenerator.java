package tom.library.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tom.library.factory.old.Parser;
import examples.factory.Garage;
//import examples.factory.Room;
import examples.factory.Student;
import examples.factory.StudentWithCar;

/**
 * It initializes Apache Velocity template engine, call the parse method on a
 * class and output the template filled with the information from parsed class
 * It implements the singleton pattern
 * 
 * @author Ahmad
 * 
 */
public class FactoryGenerator {

    /**
     * path to the templates directory
     */
    private String templatePath;

    /**
     * path to directory where generated XFactory.java files should be saved
     */
    private String generationPath;

    /**
     * path to directory where compiled XFactory.class files should be saved
     */
    private String compilationPath;

    /**
     * the only FactoryGenerator instance
     */
    private static FactoryGenerator generator;

    /**
     * the factories that have been generated so far of the form
     * <"nameOfClassToEnumerate", "nameOfFactoryClass">
     */
    private Map<Class<?>, String> generatedFactories;

    /**
     * represents names of classes that we need to generate factories for a
     * factory will be generated for each class in this queue provided that it
     * does not exist already in generated factories
     */
    private Queue<Class<?>> classesToProcess;
    
    /**
     * factory template to be filled
     */
    private Template template;

    /**
     * initiates generator and sets all paths to default values TODO: could be
     * read from configurations file
     */
    private FactoryGenerator() {
        // init Apache Velocity
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        
        this.templatePath = "./src/tom/library/factory/templates/";
        this.template = ve.getTemplate(templatePath + "FactoryTemplate.vm");
        this.generationPath = "./src/examples/factory/";
        this.compilationPath = "./src/examples/factory/";
        this.generatedFactories = new HashMap<Class<?>, String>();
        this.classesToProcess = new LinkedList<Class<?>>();
//        generatedFactories.put("examples.factory.Student", "examples.factory.tests.StudentFactory"); // for testing

    }
    
    /**
     * returning a reference to the single instance
     * 
     * @return the single FactoryGenerator instance
     */
    public static FactoryGenerator getInstance() {
        if (generator == null) {
            generator = new FactoryGenerator();
        }
        return generator;
    }

    /**
     * generates source code files for factories starting from the specified
     * class then its dependencies
     * 
     * @param classToGenerateFactoriesFor
     *            entry point for factories generation
     */
    public <T> void generateSources(Class<T> classToGenerateFactoriesFor) {
        classesToProcess.add(classToGenerateFactoriesFor);
        while (!classesToProcess.isEmpty()) {
            generateSourceForClass(classesToProcess.poll());
        }
        System.out.println("sources generated");
    }

    /**
     * generate source code of corresponding factory for a class
     * 
     * @param classToGenerateFactoryFor
     */
    public <T> void generateSourceForClass(Class<T> classToGenerateFactoryFor) {
        // parse class
        ParsedClass parsedClass = null;
//        try {
//            parsedClass = Parser.parse(Class.forName(classToGenerateFactoryFor));
            parsedClass = Parser.parse(classToGenerateFactoryFor);
//        } catch (ClassNotFoundException e1) {
//            e1.printStackTrace();
//        }
        
        // generate code using velocity
        VelocityContext context = new VelocityContext();
        context.put("parsedClass", parsedClass);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        // save code to a file
        try {
            PrintWriter pw = new PrintWriter(generationPath + parsedClass.getFactoryClassName()
                + ".java");
            pw.print(writer.toString());
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // handle dependencies
        generatedFactories.put(classToGenerateFactoryFor, parsedClass.getFactoryClassName());
        for (Class dependency : parsedClass.getDependencies()) {
            if (!generatedFactories.containsKey(dependency)) {
                classesToProcess.add(dependency);
            }
        }

        System.out.println("factory source generated for class" + classToGenerateFactoryFor);

    }

    /**
     * gets the name of classToEnemerate, look it up in the map, and get name of
     * corresponding factory class then loads and instantiates the factory
     * 
     * @param nameOfClassToEnumerate
     * @return an instance of the corresponding factory class
     */
    public Class<?> getFactoryClass(String nameOfClassToEnumerate) {
        String factoryClassName = generatedFactories.get(nameOfClassToEnumerate);
        Object factoryInstance = null;
        Class<?> factoryClass = null;
        try {
            File compilationDir = new File(compilationPath);
            URL[] urls = new URL[] { compilationDir.toURI().toURL() };
            URLClassLoader classLoader = URLClassLoader.newInstance(urls);
            factoryClass = classLoader.loadClass(factoryClassName);
            System.out.println(factoryClass.getCanonicalName());
            //factoryInstance = factoryClass.newInstance();
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return factoryClass;
    }

    public static void main(String[] args) {
        FactoryGenerator generator = FactoryGenerator.getInstance();
        generator.generateSources(Garage.class);
//        generator.generateSources(Room.class);

    }

}
