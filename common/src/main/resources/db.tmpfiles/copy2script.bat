del result.sql

echo.>>result.sql
type workout_public_m_users.sql >>result.sql

echo.>>result.sql
type workout_public_m_user_history.sql >>result.sql

echo.>>result.sql
type workout_public_m_roles.sql >>result.sql

echo.>>result.sql
type workout_public_m_levels.sql >>result.sql

echo.>>result.sql
type workout_public_m_features.sql >>result.sql

echo.>>result.sql
type workout_public_m_activity_state.sql >>result.sql

echo.>>result.sql
type workout_public_m_trainings.sql >>result.sql

echo.>>result.sql
type workout_public_m_groups.sql >>result.sql

echo.>>result.sql
type workout_public_m_activity.sql >>result.sql

echo.>>result.sql
type workout_public_m_training_programs.sql >>result.sql

echo.>>result.sql
type workout_public_l_user_roles.sql >>result.sql

echo.>>result.sql
type workout_public_l_training_features.sql >>result.sql

echo.>>result.sql
type workout_public_l_training_levels.sql

echo.>>result.sql
type workout_public_l_user_groups.sql >>result.sql

copy result.sql ..\migration\V1_3__dump.sql
