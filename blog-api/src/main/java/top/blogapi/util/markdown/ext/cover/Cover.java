package top.blogapi.util.markdown.ext.cover;

import org.commonmark.node.CustomNode;
import org.commonmark.node.Delimited;

public class Cover extends CustomNode implements Delimited {

    private final static String DELIMITER =  "%%";

    @Override
    public String getOpeningDelimiter() {
        return DELIMITER;
    }

    @Override
    public String getClosingDelimiter() {
        return DELIMITER;
    }
}
