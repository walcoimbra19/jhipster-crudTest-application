import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterCrudTestApplicationSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [JhipsterCrudTestApplicationSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class JhipsterCrudTestApplicationHomeModule {}
