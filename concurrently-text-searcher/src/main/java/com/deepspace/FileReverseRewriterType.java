package com.deepspace;

import com.deepspace.rewriter.FileReverseRewriter;
import com.deepspace.rewriter.MonoThreadFileReverseRewriter;
import com.deepspace.rewriter.MultiThreadFileReverseRewriter;
import com.deepspace.rewriter.PrioritizedFileReverseRewriter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;

@Getter
@AllArgsConstructor
public enum FileReverseRewriterType {

    MONO("MonoThreadDirectoryFileReverseRewriter", MonoThreadFileReverseRewriter.class),
    MULTI_THREAD("MultiThreadDirectoryFileReverseRewriter", MultiThreadFileReverseRewriter.class),
    PRIORITIZED_THREAD("PrioritizedDirectoryFileReverseRewriter", PrioritizedFileReverseRewriter.class);

    private final String name;

    private final Class<? extends FileReverseRewriter> fileReverseRewriter;

    public static FileReverseRewriter instantiate(FileReverseRewriterType type, String path) {
        try {
            return type.getFileReverseRewriter()
                    .getConstructor(String.class)
                    .newInstance(path);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
