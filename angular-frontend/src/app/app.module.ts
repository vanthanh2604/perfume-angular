import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { PerfumeDetailComponent } from './component/perfumer/perfume-detail/perfume-detail.component';
import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastsContainer } from './component/toast-global/toast-container.component';
import { PerfumeListComponent } from './component/perfumer/perfume-list/perfume-list.component';
import { PerfumeFormComponent } from './component/perfumer/perfume-form/perfume-form.component';
import { InputDetailComponent } from './component/input-output/input-detail/input-detail.component';
import { OutputDetailComponent } from './component/input-output/output-detail/output-detail.component';
import { InputsComponent } from './component/input-output/inputs/inputs.component';
import { OutputCreateComponent } from './component/output-create/output-create.component';
import { InputCreateComponent } from './component/input-create/input-create.component';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { StatisticalComponent } from './component/statistical/statistical/statistical.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { ChartsModule } from 'ng2-charts';
import { InventoryStatisticsComponent } from './component/statistical/inventory-statistics/inventory-statistics.component';
import { ControlMessageComponent } from './component/validate-message/control-message.component';
import { NgxCurrencyModule } from "ngx-currency";
import {
  NgxAwesomePopupModule,
  ConfirmBoxConfigModule
} from '@costlydeveloper/ngx-awesome-popup';
import { AutoFocusDirectiveDirective } from './component/perfumer/perfume-form/auto-focus-directive.directive';
import { Ng2OrderModule } from 'ng2-order-pipe';
import { CanDeactivateGuard } from './deactivate/can-deactivate/can-deactivate.guard';

@NgModule({
  declarations: [
    AppComponent,
    PerfumeListComponent,
    PerfumeDetailComponent,
    PerfumeFormComponent,
    ToastsContainer,
    InputsComponent,
    InputDetailComponent,
    OutputDetailComponent,
    OutputCreateComponent,
    ControlMessageComponent,
    InputCreateComponent,
    StatisticalComponent,
    DashboardComponent,
    InventoryStatisticsComponent,
    AutoFocusDirectiveDirective
  ],
  imports: [
    TooltipModule,
    NgxCurrencyModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule, ChartsModule,
    RouterModule.forRoot([
      { path: 'perfumes', component: PerfumeListComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'create', component: PerfumeFormComponent,canDeactivate: [CanDeactivateGuard]},
      { path: 'edit/:id', component: PerfumeFormComponent },
      { path: 'detail/:id', component: PerfumeDetailComponent },
      { path: 'input', component: InputsComponent },
      { path: 'input-detail/:id', component: InputDetailComponent },
      { path: 'output-detail/:id', component: OutputDetailComponent },
      { path: 'input-create', component: InputCreateComponent,canDeactivate: [CanDeactivateGuard]},
      { path: 'output-create', component: OutputCreateComponent ,canDeactivate: [CanDeactivateGuard] },
      { path: 'statistical', component: StatisticalComponent },
    ]),
    ConfirmationPopoverModule.forRoot({
      confirmButtonType: 'danger'
    }),
    NgxAwesomePopupModule.forRoot(),
    ConfirmBoxConfigModule.forRoot(),
    NgbModule,
  ],
  providers: [CanDeactivateGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
