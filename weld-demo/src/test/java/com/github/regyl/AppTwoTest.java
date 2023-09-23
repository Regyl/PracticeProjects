package com.github.regyl;

import com.github.regyl.lab2.AppTwo;
import com.github.regyl.lab2.service.decorator.FullTextCaseInsensitiveWardSearcherImpl;
import com.github.regyl.lab2.service.decorator.PartWordSearcherImpl;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTwoTest {
    
    @Test
    public void shouldFindLastWord() {
        String text = "Some useless text";
        String target = "text";
        
        Collection<String> resultWordCollection = AppTwo.findWord(text, target);
        
        assertThat(resultWordCollection).hasSize(1);
        assertThat(resultWordCollection).containsExactly(target);
    }
    
    @Test
    public void shouldFindFirstWord() {
        String text = "text is useless";
        String target = "text";
        
        Collection<String> resultWordCollection = AppTwo.findWord(text, target);
        
        assertThat(resultWordCollection).hasSize(1);
        assertThat(resultWordCollection).containsExactly(target);
    }
    
    @Test
    public void shouldFindMiddleWord() {
        String text = "Some text is useless";
        String target = "text";
        
        Collection<String> resultWordCollection = AppTwo.findWord(text, target);
        
        assertThat(resultWordCollection).hasSize(1);
        assertThat(resultWordCollection).containsExactly(target);
    }
    
    @Test
    public void shouldFindTwoWords() {
        String text = "Some text is useless. Some text is useless";
        String target = "text";
        
        Collection<String> resultWordCollection = AppTwo.findWord(text, target);
        
        assertThat(resultWordCollection).hasSize(2);
        assertThat(resultWordCollection).containsExactly(target, target);
    }
    
    @Test
    public void shouldFindWordEndsWithDot() {
        String text = "Some text is useless.";
        String target = "text";
        
        Collection<String> resultWordCollection = AppTwo.findWord(text, target);
        
        assertThat(resultWordCollection).hasSize(1);
        assertThat(resultWordCollection).containsExactly(target);
    }
    
    @Test
    public void shouldFindWordStartsWithDot() {
        String text = ".Some text is useless";
        String target = "text";
        
        Collection<String> resultWordCollection = AppTwo.findWord(text, target);
        
        assertThat(resultWordCollection).hasSize(1);
        assertThat(resultWordCollection).containsExactly(target);
    }
    
    @Test
    public void shouldFindWordEndsWithComma() {
        String text = "Some text is useless,";
        String target = "text";
        
        Collection<String> resultWordCollection = AppTwo.findWord(text, target);
        
        assertThat(resultWordCollection).hasSize(1);
        assertThat(resultWordCollection).containsExactly(target);
    }
    
    /**
     * Tests for case-insensitive search {@link FullTextCaseInsensitiveWardSearcherImpl}.
     */
    public static final class CaseInsensitiveTests {
    
            @Test
            public void shouldFindLastWord() {
                String text = "Some useless TEXT";
                String target = "text";
                
                Collection<String> resultWordCollection = AppTwo.findWord(text, target);
                
                assertThat(resultWordCollection).hasSize(1);
                assertThat(resultWordCollection).containsExactly("TEXT");
            }
            
            @Test
            public void shouldFindFirstWord() {
                String text = "TEXT is useless";
                String target = "text";
                
                Collection<String> resultWordCollection = AppTwo.findWord(text, target);
                
                assertThat(resultWordCollection).hasSize(1);
                assertThat(resultWordCollection).containsExactly("TEXT");
            }
            
            @Test
            public void shouldFindMiddleWord() {
                String text = "Some TEXT is useless";
                String target = "text";
                
                Collection<String> resultWordCollection = AppTwo.findWord(text, target);
                
                assertThat(resultWordCollection).hasSize(1);
                assertThat(resultWordCollection).containsExactly("TEXT");
            }
    }
    
    /**
     * Tests for partial search {@link PartWordSearcherImpl}.
     */
    public static final class PartialSearchTests {
        
        @Test
        public void shouldFindLastWord() {
            String text = "Some useless text";
            String target = "tex";
            
            Collection<String> resultWordCollection = AppTwo.findWord(text, target);
            
            assertThat(resultWordCollection).hasSize(1);
            assertThat(resultWordCollection).containsExactly("text");
        }
        
        @Test
        public void shouldFindFirstWord() {
            String text = "text is useless";
            String target = "tex";
            
            Collection<String> resultWordCollection = AppTwo.findWord(text, target);
            
            assertThat(resultWordCollection).hasSize(1);
            assertThat(resultWordCollection).containsExactly("text");
        }
        
        @Test
        public void shouldFindMiddleWord() {
            String text = "Some text is useless";
            String target = "tex";
            
            Collection<String> resultWordCollection = AppTwo.findWord(text, target);
            
            assertThat(resultWordCollection).hasSize(1);
            assertThat(resultWordCollection).containsExactly("text");
        }
        
        @Test
        public void shouldFindTwoWords() {
            String text = "Some text is useless. Some text is useless";
            String target = "tex";
            
            Collection<String> resultWordCollection = AppTwo.findWord(text, target);
            
            assertThat(resultWordCollection).hasSize(2);
            assertThat(resultWordCollection).containsExactly("text", "text");
        }
        
        @Test
        public void shouldFindWordEndsWithDot() {
            String text = "Some text is useless.";
            String target = "tex";
            
            Collection<String> resultWordCollection = AppTwo.findWord(text, target);
            
            assertThat(resultWordCollection).hasSize(1);
            assertThat(resultWordCollection).containsExactly("text");
        }
        
        @Test
        public void shouldFindWordStartsWithDot() {
            String text = ".Some text is useless";
            String target = "tex";
            
            Collection<String> resultWordCollection = AppTwo.findWord(text, target);
            
            assertThat(resultWordCollection).hasSize(1);
            assertThat(resultWordCollection).containsExactly("text");
        }
        
        @Test
        public void shouldFindWordEndsWithComma() {
            String text = "Some text is useless,";
            String target = "tex";
            
            Collection<String> resultWordCollection = AppTwo.findWord(text, target);
            
            assertThat(resultWordCollection).hasSize(1);
            assertThat(resultWordCollection).containsExactly("text");
        }
    }
}
