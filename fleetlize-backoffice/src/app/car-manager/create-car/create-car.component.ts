import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CarModel } from '../car-model';
import { CarService } from '../car.service';

@Component({
  selector: 'fleet-create-car',
  templateUrl: './create-car.component.html',
  styleUrls: ['./create-car.component.scss']
})
export class CreateCarComponent implements OnInit {

  form: FormGroup;
  models: Array<CarModel>;
  dailyPrice: number;

  public ngOnInit() {

    this.form = this.initialForm();

    this.carService.getAllModels()
      .subscribe(data => {
        this.models = data;
      });

  }

  constructor(private formBuilder: FormBuilder, private carService: CarService) {}

  onModelChange() {
    const selectedModel = this.form.get('model').value;

    if (selectedModel) {
      this.dailyPrice = selectedModel.category.dailyPrice;
    }

  }

  onSubmit() {

    if (this.form.invalid) {
      return;
    }

    const car = {...this.form.value, carModel: this.form.value.model.id};

    this.carService.createCar(car)
      .subscribe(data => {
        this.form.reset();
        console.log(data);

        this.form = this.initialForm();

      });
  }

  initialForm() {
    return this.formBuilder.group({
      name: [null, [Validators.required]],
      model: [null, [Validators.required]],
      mileage: [0, [Validators.required]],
      plate: [null, [Validators.required, Validators.maxLength(7), Validators.minLength(7)]],
    });
  }

}
