package com.aurorapixel.cortexai;

import java.io.File;
import java.io.IOException;

public class CreateGitKeep {
    public static void main(String[] args) {
        String rootDir = ".";  // 当前目录
        scanAndCreateGitkeep(new File(rootDir));
    }

    private static void scanAndCreateGitkeep(File dir) {
        if (!dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files != null && files.length == 0) {  // 目录为空
            createGitkeepFile(dir);
        } else {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanAndCreateGitkeep(file);  // 递归扫描子目录
                }
            }
        }
    }

    private static void createGitkeepFile(File dir) {
        File gitkeep = new File(dir, ".gitkeep");
        try {
            if (gitkeep.createNewFile()) {
                System.out.println("Created .gitkeep in: " + dir.getPath());
            }
        } catch (IOException e) {
            System.err.println("Failed to create .gitkeep in: " + dir.getPath());
            e.printStackTrace();
        }
    }
}
