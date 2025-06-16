-- This script inserts all the mock data we need for testing.
-- It will run automatically on startup.

-- 1. Insert Users (IDs will be 1, 2, 3, 4, 5, 6)
INSERT INTO users (name, email, password, role) VALUES
                                                    ('Admin User', 'admin@company.com', 'password123', 'ADMIN'),
                                                    ('Manager Mike', 'manager.mike@company.com', 'password123', 'MANAGER'),
                                                    ('Manager Mary', 'manager.mary@company.com', 'password123', 'MANAGER'),
                                                    ('Employee John', 'john.doe@company.com', 'password123', 'EMPLOYEE'),
                                                    ('Employee Jane', 'jane.smith@company.com', 'password123', 'EMPLOYEE'),
                                                    ('Employee Bob', 'bob.builder@company.com', 'password123', 'EMPLOYEE');

-- 2. Insert Projects and assign Managers using their IDs
INSERT INTO projects (name, managerid) VALUES
                                           ('Project Alpha', 2), -- Managed by Manager Mike (ID 2)
                                           ('Project Beta', 3),  -- Managed by Manager Mary (ID 3)
                                           ('Internal Development', 2); -- Also managed by Manager Mike (ID 2)

-- 3. Insert Time Off Requests
-- Status is an integer based on the ENUM order: PENDING=0, APPROVED=1, REJECTED=2
-- The columns user_email and request_user_number seem to be duplicates of a foreign key to the user.
-- We will insert the employee's ID into both.
INSERT INTO time_off_requests (start_date, end_date, leave_type, description, status, requested_days, user_email, request_user_number) VALUES
                                                                                                                                           ('2024-12-20', '2024-12-23', 'VACATION', 'Christmas holiday with family.', 0, 4, 4, 4), -- Request for Employee John (ID 4)
                                                                                                                                           ('2024-11-25', '2024-11-25', 'SICK', 'Doctor''s appointment.', 1, 1, 5, 5); -- Request for Employee Jane (ID 5)