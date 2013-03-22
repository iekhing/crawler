package SandBox.testing;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class Controller {
	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "/home/khing/mobilewalla/workspace/testing/data/crawl";
		int numberOfCrawlers = 1;

		CrawlConfig config = new CrawlConfig();
		
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setMaxDepthOfCrawling(0);
		config.setIncludeBinaryContentInCrawling(Boolean.TRUE);
		config.setConnectionTimeout(100000);
		
		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		controller.addSeed("https://itunes.apple.com/sg/rss/topfreeipadapplications/limit=400/genre=7003/xml");
		controller.addSeed("https://itunes.apple.com/sg/rss/toppaidapplications/limit=400/xml");
		controller.addSeed("https://itunes.apple.com/sg/rss/topfreeapplications/limit=400/genre=6023/xml");
		controller.addSeed("https://itunes.apple.com/sg/rss/topgrossingapplications/limit=400/xml");
		controller.addSeed("https://itunes.apple.com/sg/rss/topfreeapplications/limit=400/genre=6016/xml");
		controller.addSeed("https://itunes.apple.com/sg/rss/toppaidapplications/limit=400/genre=6016/xml");
		controller.addSeed("https://itunes.apple.com/sg/rss/topfreeapplications/limit=400/genre=6016/xml");
		controller.addSeed("https://itunes.apple.com/sg/rss/topgrossingapplications/limit=400/genre=6016/xml");
		
		controller.addSeed("https://itunes.apple.com/us/rss/topfreeipadapplications/limit=400/genre=7003/xml");
		controller.addSeed("https://itunes.apple.com/us/rss/toppaidapplications/limit=400/xml");
		controller.addSeed("https://itunes.apple.com/us/rss/topfreeapplications/limit=400/genre=6023/xml");
		controller.addSeed("https://itunes.apple.com/us/rss/topgrossingapplications/limit=400/xml");
		controller.addSeed("https://itunes.apple.com/us/rss/topfreeapplications/limit=400/genre=6016/xml");
		controller.addSeed("https://itunes.apple.com/us/rss/toppaidapplications/limit=400/genre=6016/xml");
		controller.addSeed("https://itunes.apple.com/us/rss/topfreeapplications/limit=400/genre=6016/xml");
		controller.addSeed("https://itunes.apple.com/us/rss/topgrossingapplications/limit=400/genre=6016/xml");
		
		//controller.addSeed("http://www.ics.uci.edu/~lopes/");
		//controller.addSeed("http://www.ics.uci.edhttp://itunes.apple.com/us/rss//");

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(MyCrawler.class, numberOfCrawlers);
	}
}