package Basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

class Basics {

    public static void main(String[] args) {
        // If an input/output file exists next to the package folder, redirect System.in/out
        try {
            String pkg = Basics.class.getPackage() != null ? Basics.class.getPackage().getName().replace('.', File.separatorChar) : "";
            String cwd = System.getProperty("user.dir");

            File inFile = pkg.isEmpty() ? new File(cwd, "input.txt") : new File(cwd, pkg + File.separator + "input.txt");
            if (!inFile.exists()) inFile = new File(cwd, "input.txt");
            if (inFile.exists()) System.setIn(new FileInputStream(inFile));

            File outFile = pkg.isEmpty() ? new File(cwd, "output.txt") : new File(cwd, pkg + File.separator + "output.txt");
            if (!outFile.exists()) outFile = new File(cwd, "output.txt");
            if (!outFile.exists()) outFile.getParentFile().mkdirs();
            System.setOut(new PrintStream(new FileOutputStream(outFile)));
        } catch (Exception ignored) {
        }

        System.out.println("Hello, World!");
    }
}