import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { PerfumeDetailComponent } from './component/perfumer/perfume-detail/perfume-detail.component';
import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import {NgxPaginationModule} from 'ngx-pagination';
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
    InputCreateComponent, StatisticalComponent, DashboardComponent
  ],
  imports: [
    TooltipModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
    RouterModule.forRoot([
      { path: 'perfumes', component: PerfumeListComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: '', redirectTo:'dashboard', pathMatch:'full'},
      { path: 'create', component: PerfumeFormComponent },
      { path: 'edit/:id', component: PerfumeFormComponent},
      { path: 'detail/:id', component: PerfumeDetailComponent },
      { path: 'input', component: InputsComponent },
      { path: 'input-detail/:id', component: InputDetailComponent },
      { path: 'output-detail/:id', component: OutputDetailComponent },
      { path: 'input-create', component: InputCreateComponent },
      { path: 'output-create', component: OutputCreateComponent },
      { path: 'statistical', component: StatisticalComponent },
    ]),
    ConfirmationPopoverModule.forRoot({
      confirmButtonType: 'danger'
    }),
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
