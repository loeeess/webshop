import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WebshopItemComponent } from './webshop-item.component';

describe('WebshopItemComponent', () => {
  let component: WebshopItemComponent;
  let fixture: ComponentFixture<WebshopItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WebshopItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WebshopItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
