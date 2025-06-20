import { User } from './user';

export interface TimeOffRequest {
  id: number;
  startDate: string; // Using string for simplicity, can be Date object
  endDate: string;
  reason: string;
  status: 'PENDING' | 'APPROVED' | 'REJECTED';
  user: User;
}
