package sk.best.newtify.web.gui.component.article;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.StreamResource;
import org.springframework.lang.Nullable;
import sk.best.newtify.api.dto.ArticleDTO;

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * @author Marek Urban
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
public class ArticleDialogComponent extends Dialog {

    private static final long              serialVersionUID = -8124048656164601926L;
    private static final DateTimeFormatter DATE_FORMATTER   = DateTimeFormatter.ofPattern("d.MM.uuuu");

    private final Image    image       = new Image();
    private final H2       title       = new H2();
    private final Span     author      = new Span();
    private final Span     date        = new Span();
    private final H5       previewText = new H5();
    private final TextArea content     = new TextArea();

    public ArticleDialogComponent() {
        init();
    }

    protected void init() {
        styleImage();
        styleTitle();
        styleAuthor();
        stylePreviewText();
        styleContent();

        Span titleAndAuthor = new Span(title, author, date);
        titleAndAuthor.getStyle()
                .set("margin-left", "0.5em");

        add(image, titleAndAuthor, previewText, content);
    }

    public void setArticle(@Nullable ArticleDTO article,
                           @Nullable byte[] imageData) {
        if (article == null) {
            clear();
            return;
        }

        if (imageData != null && imageData.length != 0) {
            setImage(imageData);
        }


        title.setText(article.getTitle());
        previewText.setText(article.getShortTitle());
        author.setText(article.getAuthor());
        content.setValue(article.getText());

        date.setText(DATE_FORMATTER.format(
                        Instant.ofEpochSecond(article.getCreatedAt())
                                .atZone(ZoneId.systemDefault())
                )
        );
    }

    private void setImage(@Nullable byte[] imageBytes) {
        if (imageBytes == null) {
            image.removeAll();
            return;
        }

        StreamResource streamResource = new StreamResource(
                UUID.randomUUID() + ".jpeg",
                () -> new ByteArrayInputStream(imageBytes)
        );
        image.setSrc(streamResource);
    }

    private void clear() {
        title.removeAll();
        previewText.removeAll();
        author.removeAll();
        date.removeAll();
        content.clear();

        image.removeAll();
        image.setSrc("");
    }

    private void styleImage() {
        image.getStyle()
                .set("width", "100%")
                .set("height", "auto")
                .set("border-radius", "1em 1em 0 0");
    }

    private void styleTitle() {
        title.getStyle()
                .set("margin", "0");
    }

    private void styleAuthor() {
        Icon icon = VaadinIcon.USER.create();
        icon.getStyle().set("margin-left", "0.5em");
        author.add(icon);
        author.getElement().getThemeList().add("badge primary");
        author.getStyle()
                .set("margin", "0.75em 1.5em 0em auto");
    }

    private void stylePreviewText() {
        previewText.getStyle()
                .set("font-style", "italic")
                .set("color", "gray")
                .set("margin-left", "2em");
    }

    private void styleContent() {
        content.setReadOnly(true);
        content.setWidthFull();
    }

    public class URLShortener {
        // storage for generated keys
        private HashMap<String, String> keyMap; // key-url map
        private HashMap<String, String> valueMap;// url-key map to quickly check
        // whether an url is
        // already entered in our system
        private String domain; // Use this attribute to generate urls for a custom
        // domain name defaults to http://fkt.in
        private char myChars[]; // This array is used for character to number
        // mapping
        private Random myRand; // Random object used to generate random integers
        private int keyLength; // the key length in URL defaults to 8

        // Default Constructor
        URLShortener() {
            keyMap = new HashMap<String, String>();
            valueMap = new HashMap<String, String>();
            myRand = new Random();
            keyLength = 8;
            myChars = new char[62];
            for (int i = 0; i < 62; i++) {
                int j = 0;
                if (i < 10) {
                    j = i + 48;
                } else if (i > 9 && i <= 35) {
                    j = i + 55;
                } else {
                    j = i + 61;
                }
                myChars[i] = (char) j;
            }
            domain = "http://fkt.in";
        }

        // Constructor which enables you to define tiny URL key length and base URL
        // name
        URLShortener(int length, String newDomain) {
            this();
            this.keyLength = length;
            if (!newDomain.isEmpty()) {
                newDomain = sanitizeURL(newDomain);
                domain = newDomain;
            }
        }

        // shortenURL
        // the public method which can be called to shorten a given URL
        public String shortenURL(String longURL) {
            String shortURL = "";
            if (validateURL(longURL)) {
                longURL = sanitizeURL(longURL);
                if (valueMap.containsKey(longURL)) {
                    shortURL = domain + "/" + valueMap.get(longURL);
                } else {
                    shortURL = domain + "/" + getKey(longURL);
                }
            }
            // add http part
            return shortURL;
        }

        // expandURL
        // public method which returns back the original URL given the shortened url
        public String expandURL(String shortURL) {
            String longURL = "";
            String key = shortURL.substring(domain.length() + 1);
            longURL = keyMap.get(key);
            return longURL;
        }

        // Validate URL
        // not implemented, but should be implemented to check whether the given URL
        // is valid or not
        boolean validateURL(String url) {
            return true;
        }

        // sanitizeURL
        // This method should take care various issues with a valid url
        // e.g. www.google.com,www.google.com/, http://www.google.com,
        // http://www.google.com/
        // all the above URL should point to same shortened URL
        // There could be several other cases like these.
        String sanitizeURL(String url) {
            if (url.substring(0, 7).equals("http://"))
                url = url.substring(7);

            if (url.substring(0, 8).equals("https://"))
                url = url.substring(8);

            if (url.charAt(url.length() - 1) == '/')
                url = url.substring(0, url.length() - 1);
            return url;
        }

        /*
         * Get Key method
         */
        private String getKey(String longURL) {
            String key;
            key = generateKey();
            keyMap.put(key, longURL);
            valueMap.put(longURL, key);
            return key;
        }

        // generateKey
        private String generateKey() {
            String key = "";
            boolean flag = true;
            while (flag) {
                key = "";
                for (int i = 0; i <= keyLength; i++) {
                    key += myChars[myRand.nextInt(62)];
                }
                // System.out.println("Iteration: "+ counter + "Key: "+ key);
                if (!keyMap.containsKey(key)) {
                    flag = false;
                }
            }
            return key;
        }


        }
}
