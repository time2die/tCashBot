package org.time2die.cashbot;
/**
 * Created by time2die on 17.10.16.
 */

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.URL;

public class CashBot {

    public static final String GOOGLE_ACCOUNT_USERNAME = "test@gmail.com";
    public static final String GOOGLE_ACCOUNT_PASSWORD = "testte";
    public static final String SPREADSHEET_URL =
            "https://docs.google.com/spreadsheets/d/1AgcVkA-fgKUL4m7VvoeHW5efmY5M73oeyhi51iWYw1k/edit?usp=sharing";

    public static void main(String[] args) throws ServiceException, IOException {

        /** Our view of Google Spreadsheets as an authenticated Google user. */
        SpreadsheetService service = new SpreadsheetService("Print Google Spreadsheet Demo");

        // Login and prompt the user to pick a sheet to use.
        service.setUserCredentials(GOOGLE_ACCOUNT_USERNAME, GOOGLE_ACCOUNT_PASSWORD);

        // Load sheet
        URL metafeedUrl = new URL(SPREADSHEET_URL);
        SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl, SpreadsheetEntry.class);
        URL listFeedUrl = ((WorksheetEntry) spreadsheet.getWorksheets().get(0)).getListFeedUrl();

        // Print entries
        ListFeed feed = (ListFeed) service.getFeed(listFeedUrl, ListFeed.class);
        for (ListEntry entry : feed.getEntries()) {
            System.out.println("new row");
            for (String tag : entry.getCustomElements().getTags()) {
                System.out.println("     " + tag + ": " + entry.getCustomElements().getValue(tag));
            }
        }
    }
}