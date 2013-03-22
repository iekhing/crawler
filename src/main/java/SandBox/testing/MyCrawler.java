package SandBox.testing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {

	Logger LOGGER = LoggerFactory.getLogger(MyCrawler.class);

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar))$");

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		LOGGER.info("URL: {} ", url);
		return !FILTERS.matcher(href).matches();
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		LOGGER.info("URL: {} ", url);
		LOGGER.info("Content Type {} s", page.getContentType());
		// LOGGER.info(new String (page.getContentData(), "UTF-8"));
		InputStream in = new ByteArrayInputStream(page.getContentData());
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		try {
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					String localPart = event.asStartElement().getName()
							.getLocalPart();
					if (StringUtils.equals(localPart, "title")) {
						String data = null;
						event = eventReader.nextEvent();
						if (event instanceof Characters) {
							data = event.asCharacters().getData();
						}
						LOGGER.info("event name {} = {} ", localPart, data);
					}
					// If we have a item element we create a new item
				}
			}
		} catch (XMLStreamException e) {
			LOGGER.error(e.getMessage(), e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (page.getParseData() instanceof TextParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			List<WebURL> links = htmlParseData.getOutgoingUrls();

			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());
		}
	}
}