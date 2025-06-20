import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerPartComponent } from './manager-part.component';

describe('ManagerPartComponent', () => {
  let component: ManagerPartComponent;
  let fixture: ComponentFixture<ManagerPartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagerPartComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ManagerPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
