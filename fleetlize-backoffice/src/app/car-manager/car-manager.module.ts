import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CarManagerRoutingModule } from './car-manager-routing.module';
import { CarManagerComponent } from './car-manager.component';
import { CreateCarComponent } from './create-car/create-car.component';

import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

import { HttpClientModule } from '@angular/common/http';
import { CarService } from './car.service';
import { ListCarComponent } from './list-car/list-car.component';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [
    CarManagerComponent,
    CreateCarComponent,
    ListCarComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    CarManagerRoutingModule,
    MatSelectModule,
    MatTableModule,
  ],
  providers: [
    CarService
  ]
})
export class CarManagerModule { }
