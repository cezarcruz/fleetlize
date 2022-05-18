import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { CarModel } from './car-model';

const urlCarManager = '/api/v1/car-manager';

@Injectable()
export class CarService {

  constructor(private httpClient: HttpClient) {}

  public getAllModels() {
    return this.httpClient.get<CarModel[]>(environment.gateway + urlCarManager + '/car-model/');
  }

  public createCar(data: any) {
    return this.httpClient.post(environment.gateway + urlCarManager + '/car', data);
  }

  public getAllCars() {
    return this.httpClient.get<any[]>(environment.gateway + urlCarManager + '/car');
  }

}
