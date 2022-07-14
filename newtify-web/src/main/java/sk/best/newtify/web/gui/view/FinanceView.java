package sk.best.newtify.web.gui.view;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.ObjectFactory;
import sk.best.newtify.api.FinancesApi;
import sk.best.newtify.api.dto.FinanceDTO;
import sk.best.newtify.api.dto.ETopicType;
import sk.best.newtify.web.gui.component.finance.FinancePreviewComponent;
import sk.best.newtify.web.gui.component.widget.cryptoNameWidgetComponent;
import sk.best.newtify.web.gui.layout.MainLayout;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * @author Marek Urban
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
@PageTitle("Finance")
@Route(value = "finance", layout = MainLayout.class)
public class FinanceView {

    private static final long serialVersionUID = 4107656392983873277L;

    private final FinancesApi                           financesApi;
    private final ObjectFactory<FinancePreviewComponent> financePreviewObjectFactory;
    private final ObjectFactory<cryptoNameWidgetComponent>  cryptoNameWidgetComponentObjectFactory;

    private final VerticalLayout middleContent      = new VerticalLayout();
    private final VerticalLayout leftWidgetContent  = new VerticalLayout();
    private final VerticalLayout rightWidgetContent = new VerticalLayout();

    private List<cryptoNameDTO> finances = Collections.emptyList();

    public FinanceView(FinancesApi financesApi,
                      ObjectFactory<FinancePreviewComponent> finacnePreviewObjectFactory,
                      ObjectFactory<cryptoNameWidgetComponent> cryptoNameWidgetComponentObjectFactory) {
        this.financesApi                         = financesApi;
        this.financePreviewObjectFactory         = financePreviewObjectFactory;
        this.cryptoNameWidgetComponentObjectFactory = cryptoNameWidgetComponentObjectFactory;
    }

    @PostConstruct
    protected void init() {
        createMainPane();
        createLeftWidgetPane();
        createRightWidgetPane();

        add(leftWidgetContent, middleContent, rightWidgetContent);
    }

    private void createMainPane() {
        middleContent.removeAll();
        middleContent.setAlignItems(FlexComponent.Alignment.CENTER);
        setFlexShrink(1, middleContent);
        setFlexGrow(2, middleContent);

        fetchFinances();
        for (cryptoNameDTO finance : finances) {
            FinancePreviewComponent previewComponent = financePreviewObjectFactory.getObject();
            previewComponent.setFinance(finance);
            middleContent.add(previewComponent);
        }
    }

    private void fetchFinances() {
        finances = financesApi.retrieveFinancess(ETopicType.FINANCE.getValue()).getBody();
    }
}
