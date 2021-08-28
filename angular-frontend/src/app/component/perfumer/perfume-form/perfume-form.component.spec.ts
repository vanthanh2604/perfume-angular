import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfumeFormComponent } from './perfume-form.component';

describe('PerfumeFormComponent', () => {
  let component: PerfumeFormComponent;
  let fixture: ComponentFixture<PerfumeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PerfumeFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PerfumeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
