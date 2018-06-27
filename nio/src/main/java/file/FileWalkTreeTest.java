package file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileWalkTreeTest {
    public static void main(String[] args) {
        Path path = Paths.get(".");

        try {
            Files.walkFileTree(path, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("pre visit: "+ dir.getFileName());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("visit file: "+ file.getFileName());
                    return FileVisitResult.SKIP_SIBLINGS;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("visit dir: "+ dir.toAbsolutePath());
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
