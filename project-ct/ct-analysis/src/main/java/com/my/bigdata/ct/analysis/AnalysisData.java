package com.my.bigdata.ct.analysis;

import com.my.bigdata.ct.analysis.tool.AnalysisBeanTool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 分析数据
 */
public class AnalysisData {
    public static void main(String[] args) throws Exception {

        //int result = ToolRunner.run( new AnalysisTextTool(), args );
        int result = ToolRunner.run( new AnalysisBeanTool(), args );

    }
}