package top.blogapi.util.markdown.ext.cover.internal;

import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

public class CoverDelimiterProcessor implements DelimiterProcessor {
    @Override
    public char getOpeningCharacter() {
        return '%';
    }

    @Override
    public char getClosingCharacter() {
        return '%';
    }

    @Override
    public int getMinLength() {
        return 2;
    }

    @Override
    public int process(DelimiterRun opener, DelimiterRun closer) {
        return opener.length() > 1 && closer.length() > 1 ? 2 : 0;
    }
}
