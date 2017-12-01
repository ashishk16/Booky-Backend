//package com.bookrental.Models;
//
//import org.junit.Assert;
//import org.junit.Test;
//import java.util.Arrays;
//
//
//
//
//public class BookTest {
//    private Book book;
//    @org.junit.Before
//    public void setUp() throws Exception {
//        book = new Book("zyTCAlFPjgYC","The Google Story", Arrays.asList("David A. Vise","Mark Malseed"),
//                Arrays.asList("Business & Economics / Entrepreneurship","Computers / Information Technology","History / Modern / 20th Century"),
//                "\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, the book takes you inside the creation and growth of a company whose name is a favorite brand and a standard verb recognized around the world. Its stock is worth more than General Motors’ and Ford’s combined, its staff eats for free in a dining room that used to be\u003cb\u003e \u003c/b\u003erun\u003cb\u003e \u003c/b\u003eby the Grateful Dead’s former chef, and its employees traverse the firm’s colorful Silicon Valley campus on scooters and inline skates.\u003cbr\u003e\u003cbr\u003e\u003cb\u003eTHE GOOGLE STORY \u003c/b\u003eis the definitive account of the populist media company powered by the world’s most advanced technology that in a few short years has revolutionized access to information about everything for everybody everywhere. \u003cbr\u003eIn 1998, Moscow-born Sergey Brin and Midwest-born Larry Page dropped out of graduate school at Stanford University to, in their own words, “change the world” through a search engine that would organize every bit of information on the Web for free.\u003cbr\u003e\u003cbr\u003eWhile the company has done exactly that in more than one hundred languages, Google’s quest continues as it seeks to add millions of library books, television broadcasts, and more to its searchable database. \u003cbr\u003eReaders will learn about the amazing business acumen and computer wizardry that started the company on its astonishing course; the secret network of computers delivering lightning-fast search results; the unorthodox approach that has enabled it to challenge Microsoft’s dominance and shake up Wall Street. Even as it rides high, Google wrestles with difficult choices that will enable it to continue expanding while sustaining the guiding vision of its founders’ mantra: DO NO EVIL.\"\u003cbr\u003e\u003cbr\u003e\u003cbr\u003e\u003ci\u003eFrom the Hardcover edition.\u003c/i\u003e",
//                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api",
//                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api",
//                "Random House Publishing Group","2005-11-15",50,10,3.5,20);
//    }
//
//    @Test
//    public void shouldCheckIfGettersOfBookObjectAreReturningRightInformation(){
//        Assert.assertEquals("zyTCAlFPjgYC", book.getId());
//        Assert.assertEquals("The Google Story",book.getTitle());
//        Assert.assertEquals(Arrays.asList("David A. Vise","Mark Malseed"), book.getAuthors());
//        Assert.assertEquals(Arrays.asList("Business & Economics / Entrepreneurship","Computers / Information Technology","History / Modern / 20th Century"),
//                book.getCategories());
//        Assert.assertEquals("\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, the book takes you inside the creation and growth of a company whose name is a favorite brand and a standard verb recognized around the world. Its stock is worth more than General Motors’ and Ford’s combined, its staff eats for free in a dining room that used to be\u003cb\u003e \u003c/b\u003erun\u003cb\u003e \u003c/b\u003eby the Grateful Dead’s former chef, and its employees traverse the firm’s colorful Silicon Valley campus on scooters and inline skates.\u003cbr\u003e\u003cbr\u003e\u003cb\u003eTHE GOOGLE STORY \u003c/b\u003eis the definitive account of the populist media company powered by the world’s most advanced technology that in a few short years has revolutionized access to information about everything for everybody everywhere. \u003cbr\u003eIn 1998, Moscow-born Sergey Brin and Midwest-born Larry Page dropped out of graduate school at Stanford University to, in their own words, “change the world” through a search engine that would organize every bit of information on the Web for free.\u003cbr\u003e\u003cbr\u003eWhile the company has done exactly that in more than one hundred languages, Google’s quest continues as it seeks to add millions of library books, television broadcasts, and more to its searchable database. \u003cbr\u003eReaders will learn about the amazing business acumen and computer wizardry that started the company on its astonishing course; the secret network of computers delivering lightning-fast search results; the unorthodox approach that has enabled it to challenge Microsoft’s dominance and shake up Wall Street. Even as it rides high, Google wrestles with difficult choices that will enable it to continue expanding while sustaining the guiding vision of its founders’ mantra: DO NO EVIL.\"\u003cbr\u003e\u003cbr\u003e\u003cbr\u003e\u003ci\u003eFrom the Hardcover edition.\u003c/i\u003e",
//                book.getDescription());
//        Assert.assertEquals("http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api",
//                book.getThumbnail());
//        Assert.assertEquals("http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api",
//                book.getSmallThumbnail());
//        Assert.assertEquals("Random House Publishing Group", book.getPublisher());
//        Assert.assertEquals("2005-11-15", book.getPublishedDate());
//        Assert.assertTrue(Double.compare(50, book.getPrice())==0);
//        Assert.assertEquals(10, book.getCopies());
//        Assert.assertTrue(Double.compare(10, book.getCopies()) == 0);
//        Assert.assertEquals(20, book.getRatingCount());
//        Assert.assertEquals(10,book.getCopies());
//    }
//}