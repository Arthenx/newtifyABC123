package sk.best.newtify.web.gui.component.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;

public class EmailValidation {
    public static void main(String args[]){
        List emails = new ArrayList();
        emails.add("admin@yiibai.com");
        emails.add("yes2dos@gmail.com");
        emails.add("maxsu%google-cn.com");
        emails.add("maxsua@gmail-cn.com");
        emails.add("said#@youtube.co.in");
        emails.add("atosll@domaincom");
        emails.add("kitte#gmail.com");
        emails.add("@yiibai.com");
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        for (Object email : emails) {
            Matcher matcher = pattern.matcher((CharSequence) email);
            System.out.println(email + " : " + matcher.matches());
        }
    }

/**    @PageTitle("Modal Dialogs")
    @Route(value = "")
    public class ModalDialogs extends VerticalLayout
    {
        public ModalDialogs()
        {
            UI ui = UI.getCurrent();
            add(new Button("Modal Dialog", clicked -> {
                Dialog modalDialog = new Diln("should be printed, but isn't"), System.out::println);
                }));
                modalDialog.add(new Button("Close", clickEalog(new Div(new Span("Modal dialog")));
                modalDialog.setCloseOnEsc(false);
                modalDialog.setCloseOnOutsideClick(false);
                modalDialog.setModal(true);

                modalDialog.add(new Button("Execute JS", clickEvent -> {
                    ui.getPage().executeJs("console.log($0);", Instant.now().toString())
                            .then(success -> System.out.printvent -> modalDialog.close()));
                modalDialog.setWidth("40ch");

                modalDialog.open();
            }));
        }
    }
*/
}
