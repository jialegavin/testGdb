package com.njyb.test.jiahongping.lucene;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.ChainedFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.PrefixFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.njyb.gbdbas.util.DataUtil;

public class SimpleSearch {
	public static void main(String[] args)throws Exception {
		String filePath="D:\\lucene\\china_eight_index\\2012\\02";  
		
		
        Directory dir=FSDirectory.open(new File(filePath));  
        IndexReader reader=DirectoryReader.open(dir);  
        IndexSearcher searcher=new IndexSearcher(reader);  
        
        TermQuery query=new TermQuery(new Term("trade_type","I")); 
        Filter  hscodeFilter=new PrefixFilter(new Term("hscode", "39"));
        Filter  dateFilter= NumericRangeFilter.newLongRange("date", DataUtil
				.getDateByOperator("2012-02"), DataUtil
				.getDateByOperator("2012-02"), true, true);
        
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(hscodeFilter);
		filters.add(dateFilter);
		Filter[]filtesr=filters.toArray(new Filter[filters.size()]);
		
		Filter filter = new ChainedFilter(filtesr,ChainedFilter.AND);
		System.out.println("过滤器:"+filter+":query:"+query); 
        TopDocs topdocs=searcher.search(query, filter,10);
        ScoreDoc[] scoreDocs=topdocs.scoreDocs;  
         
        for(int i=0; i < scoreDocs.length; i++) {  
            int doc = scoreDocs[i].doc;  
            Document document = searcher.doc(doc);  
            System.out.println(document.get("hscode")+":"+document.get("trade_type")+":"+document.get("date")+":"+document.get("quantity"));
        }  
        reader.close();  
	}
}
