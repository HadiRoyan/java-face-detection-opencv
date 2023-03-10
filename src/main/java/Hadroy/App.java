/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Hadroy;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.CvException;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;  

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {

        try{

        	// Loading OpenCV library
//        	 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        	System.load("C:/OpenCV/opencv/build/java/x64/opencv_java460.dll");
        	System.out.println("OpenCV library loaded!");

        	// Reading the image from the file & storing it in to a matrix object
        	String imgFile = "src/main/resources/image/facedetection_input.jpg"; 
        	Mat src = Imgcodecs.imread(imgFile);
        	System.out.println("Image loaded!");

        	// inisiating cascade classifier
        	String xmlFile = "src/main/resources/lbpCascade/lbpcascade_frontalface.xml";
        	CascadeClassifier classifier = new CascadeClassifier(xmlFile);
        	System.out.println("CascadeClassifier loaded!");

        	MatOfRect facedetections = new MatOfRect();
        	classifier.detectMultiScale(src,facedetections);
        	System.out.println("=================================");
        	System.out.println(String.format("Detect %s faces",facedetections.toArray().length));

        	for (Rect rect : facedetections.toArray()) {
        		Imgproc.rectangle(
        			src,
        			new Point(rect.x, rect.y),
        			new Point(rect.x + rect.width, rect.y + rect.height),
        			new Scalar(0,0,255),
        			3
        		);
        	}

        	String outFile = "src/main/resources/image/output.jpg"; 
        	// writing the image
        	Imgcodecs.imwrite(outFile,src);

        	System.out.println("Image Processed");
        }catch (CvException ce) {
            System.out.println("\nOpenCV Error : "+ ce);
        }catch (Exception e) {
            System.out.println("Error : "+ e);
        }

		System.exit(0);
    }
}
















