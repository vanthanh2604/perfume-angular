import { Component, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ValidateService } from './validate.service';


@Component({
  selector: 'control-message',
  template: `<div style="font-size: 12px; color:red" *ngIf="errorMessage !== null">{{errorMessage}}</div>`
})
export class ControlMessageComponent {
  @Input() control: FormControl;
  @Input() field: string;
  constructor() { }

  get errorMessage() {
    for (let propertyName in this.control.errors) {
      if (this.control.errors.hasOwnProperty(propertyName) && (this.control.touched||this.control.dirty)) {
        return ValidateService.getValidatorErrorMessage(this.field,propertyName, this.control.errors[propertyName]);
      }
    }
    return null;
  }
}