import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfumeListComponent } from './perfume-list.component';

describe('PerfumeListComponent', () => {
  let component: PerfumeListComponent;
  let fixture: ComponentFixture<PerfumeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PerfumeListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PerfumeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
