import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAddComponent } from './customer-add.component';

describe('CustomerAddComponent', () => {
  let component: CustomerAddComponent;
  let fixture: ComponentFixture<CustomerAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerAddComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
