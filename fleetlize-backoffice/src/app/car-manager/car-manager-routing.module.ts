import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CarManagerComponent } from './car-manager.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { ListCarComponent } from './list-car/list-car.component';

const routes: Routes = [
  { path: '', component: CarManagerComponent },
  { path: 'create', component: CreateCarComponent },
  { path: 'list', component: ListCarComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarManagerRoutingModule { }
