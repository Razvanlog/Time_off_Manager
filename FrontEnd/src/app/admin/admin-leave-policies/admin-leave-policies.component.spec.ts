import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLeavePoliciesComponent } from './admin-leave-policies.component';

describe('AdminLeavePoliciesComponent', () => {
  let component: AdminLeavePoliciesComponent;
  let fixture: ComponentFixture<AdminLeavePoliciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminLeavePoliciesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminLeavePoliciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
