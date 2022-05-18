import { Component, OnInit } from '@angular/core';
import { CarService } from '../car.service';

@Component({
  selector: 'fleet-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.scss'],
})
export class ListCarComponent implements OnInit {

  dataSource: any[];
  displayedColumns: string[] = ['plate', 'mileage'];

  constructor(private carService: CarService) {}

  ngOnInit() {
    this.carService.getAllCars().subscribe(data => {
      this.dataSource = data;
    });
  }

}
