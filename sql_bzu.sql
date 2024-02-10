-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:4306
-- Generation Time: Feb 10, 2024 at 09:09 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finalproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `contactus`
--

CREATE TABLE `contactus` (
  `user_id` varchar(8) NOT NULL,
  `U_email` varchar(31) NOT NULL,
  `question` varchar(512) NOT NULL,
  `qid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contactus`
--

INSERT INTO `contactus` (`user_id`, `U_email`, `question`, `qid`) VALUES
('1200640', 'eyass@gmail.com', 'hu guys', 8);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `course_id` varchar(10) NOT NULL,
  `course_name` varchar(100) DEFAULT NULL,
  `course_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `course_name`, `course_url`) VALUES
('ARAB135', 'مهارات اللغة العربية 1', 'https://www.bzu-hub.com/ARAB135'),
('ARAB136', 'مهارات اللغة العربية 2', 'https://www.bzu-hub.com/ARAB136'),
('COMP1310', 'مقدمة في الحاسوب', 'https://www.bzu-hub.com/COMP1310'),
('COMP1331', 'الحاسوب والبرمجة', 'https://www.bzu-hub.com/COMP1331'),
('COMP2311', 'البرمجة الشيئية', 'https://www.bzu-hub.com/COMP2311'),
('COMP233', 'الرياضيات المنفصلة', 'https://www.bzu-hub.com/COMP233'),
('COMP242', 'بنية المعلومات', 'https://www.bzu-hub.com/COMP242'),
('COMP311', 'لينوكس', 'https://www.bzu-hub.com/COMP311'),
('COMP3330', 'إدارة قواعد البيانات', 'https://www.bzu-hub.com/COMP3330'),
('COMP334', 'تطبيقات وتقنيات الويب', 'https://www.bzu-hub.com/COMP334'),
('COMP336', 'تحليل الخوارزميات', 'https://www.bzu-hub.com/COMP336'),
('COMP338', 'الذكاء الاصطناعي', 'https://www.bzu-hub.com/COMP338'),
('COMP431', 'نظم التشغيل', 'https://www.bzu-hub.com/COMP431'),
('COMP432', 'أمن وحماية الحاسوب', 'https://www.bzu-hub.com/COMP432'),
('COMP433', 'الهندسة البرمجية', 'https://www.bzu-hub.com/COMP433'),
('COMP438', 'تطوير برامج الجوال', 'https://www.bzu-hub.com/COMP438'),
('COMP439', 'المترجمات ولغات البرمجة', 'https://www.bzu-hub.com/COMP439'),
('CULS331', 'الحضارة الأوروبية المعاصرة', 'https://www.bzu-hub.com/CULS331'),
('CULS332', 'الفكر العربي الحديث والمعاصر', 'https://www.bzu-hub.com/CULS332'),
('ENCS2110', 'مختبر الالكترونيات الرقمية', 'https://www.bzu-hub.com/ENCS2110'),
('ENCS2340', 'أنظمة رقمية', 'https://www.bzu-hub.com/ENCS2340'),
('ENCS3320', 'شبكات الحاسوب', 'https://www.bzu-hub.com/ENCS3320'),
('ENCS4130', 'مختبر شبكات الحاسوب', 'https://www.bzu-hub.com/ENCS4130'),
('MATH1321', 'التفاضل والتكامل 2', 'https://www.bzu-hub.com/MATH1321'),
('MATH1411', 'التفاضل والتكامل 1', 'https://www.bzu-hub.com/MATH1411'),
('MATH234', 'مقدمة في الجبر الخطي', 'https://www.bzu-hub.com/MATH234'),
('PHYS111', 'مختبر الفيزياء العامة 1', 'https://www.bzu-hub.com/PHYS111'),
('PHYS112', 'مختبر الفيزياء العامة 1', 'https://www.bzu-hub.com/PHYS112'),
('PHYS132', 'الفيزياء العامة 2', 'https://www.bzu-hub.com/PHYS132'),
('PHYS141', 'الفيزياء العامة 1', 'https://www.bzu-hub.com/PHYS141'),
('STAT2311', 'الإحصاء 1', 'https://www.bzu-hub.com/STAT2311');

-- --------------------------------------------------------

--
-- Table structure for table `electives`
--

CREATE TABLE `electives` (
  `id` varchar(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `hours` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `electives`
--

INSERT INTO `electives` (`id`, `name`, `hours`) VALUES
('EDUC242', 'علم النفس التربوي', 4),
('ARAB230', 'الأدب الشعبي الفلسطيني', 3),
('ARAB2301', 'مدخل إلى الأدب الفلسطيني', 3),
('ARAB231', 'مدخل الى دراسة الأدب العربي', 3),
('ARAB2390', 'العروض والقافية', 3),
('ARAB331', 'الدراسات القرانية', 3),
('CULS230', 'الديمقراطية وحقوق الانسان والقانون الدولي الإنساني', 3),
('CULS232', 'فلسطين : الماضي والحاضر', 3),
('CULS438', 'موضوع خاص : الديانات والمعتقدات الشرقية القديمة', 3),
('PHIL231', 'مدخل الى الفلسفة', 3),
('PHIL232', 'مدخل الى علم المنطق', 3),
('GERM231', 'اللغة الالمانية 1', 3),
('SPAN131', 'اللغة الاسبانية 1', 3),
('GEOG230', 'مدخل الى الجغرافية البشرية', 3),
('GEOG231', 'مدخل الى الجغرافية الطبيعية', 3),
('GEOG233', 'الجغرافية الإقتصادية', 3),
('GEOG332', 'الجغرافية السياسية', 3),
('GEOG336', 'جغرافية البيئة', 3),
('GEOG338', 'جغرافية فلسطين', 3),
('ARCH131', 'مقدمة الى علم آثار فلسطين', 3),
('ARCH230', 'مدخل إلى دراسة الآثار', 3),
('HIST133', 'معالم الحضارة العربية الاسلامية', 3),
('HIST134', 'تاريخ فلسطين منذ القرن الثامن عشر', 3),
('HIST135', 'تاريخ القدس', 3),
('HIST231', 'مدخل لدراسة التاريخ', 3),
('HIST232', 'تاريخ الشرق الاوسط القديم', 3),
('HIST233', 'تاريخ العرب قبل الاسلام', 3),
('HIST336', 'تاريخ المشرق العربي خلال القرن التاسع عشر', 3),
('HIST338', 'تاريخ فلسطين خلال الفترات الاسلامية', 3),
('HIST430', 'تاريخ اوروبا الحديث (1789-1918)', 3),
('HIST432', 'التاريخ الحديث للولايات المتحدة الامركية', 3),
('ARSK131', 'اللغة العربية الفصحى 1', 3),
('ARSK133', 'اللهجة الفلسطينية 1', 3),
('WOMS231', 'مدخل الى دراسات المرأة', 3),
('WOMS232', 'المرأة في المجتمع العربي', 3),
('WOMS234', 'علم نفس المرأة', 3),
('ARTS234', 'الفن الفلسطيني المعاصر', 3),
('MUSI231', 'مدخل الى الموسيقى', 3),
('FREN131', 'مدخل الى اللغة الفرنسية', 3),
('SOCI131', 'مدخل الى علم الاجتماع', 3),
('SOCI233', 'المجتمع العربي', 3),
('SOCW231', 'المدخل الى العمل الإجتماعي', 3),
('PSYC231', 'مدخل الى علم النفس', 3),
('PUBA130', 'مقدمة في الإدارة العامة', 3),
('PUBA231', 'مقدمة في المحاسبة الحكومية', 3),
('JURI130', 'مدخل إلى العلوم القانونية', 3),
('POLS231', 'مقدمة في العلوم السياسية', 3),
('POLS235', 'الفكر السياسي والنظريات السياسية المعاصرة', 3),
('POLS236', 'النظام السياسي الفلسطيني', 3),
('POLS333', 'القضية الفلسطينية 1', 3),
('EDUC231', 'مدخل الى التربية', 3),
('GENS120', 'الحدائق المنزلية', 2),
('GENS220', 'الانسان والتكاثر', 2),
('EDUC222', 'الأسس الإجتماعية والفلسفية للتربية', 2),
('EDUC323', 'مهنة التعليم وأخلاقياتها', 2),
('MUSI221', 'نظريات موسيقية وغناء بالنوتة 1', 2),
('ARAB111', 'الخط العربي', 1),
('GEOG210', 'قضايا البيئة في المجتمع الفلسطيني', 1),
('PHED211', 'اللياقة البدنية', 1),
('PUBA312', 'مصطلحات إدارية عامة باللغة الإنجليزية', 1),
('PUBA414', 'إدارة المكتب', 1),
('JURI211', 'المعلوماتية القانونية', 1);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_type` varchar(25) NOT NULL,
  `event_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_type`, `event_date`) VALUES
('semester_end', '2024-02-15'),
('holiday', '2024-02-28'),
('holiday 2', '2024-03-13');

-- --------------------------------------------------------

--
-- Table structure for table `markers`
--

CREATE TABLE `markers` (
  `marker_name` varchar(50) NOT NULL,
  `marker_type` varchar(32) NOT NULL,
  `lat` double NOT NULL,
  `log` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `markers`
--

INSERT INTO `markers` (`marker_name`, `marker_type`, `lat`, `log`) VALUES
('Science', 'Cafeteria', 31.958508, 35.181337),
('Business', 'Cafeteria', 31.958535, 35.183257),
('Dom', 'Cafeteria', 31.960252, 35.182003),
('Literature', 'Cafeteria', 31.960549, 35.181953),
('Nursing', 'Cafeteria', 31.962129, 35.182473),
('Supermarket', 'Cafeteria', 31.960088, 35.182452),
('It', 'College', 31.961365, 35.184454),
('Art', 'College', 31.96201, 35.182003),
('Engineering', 'College', 31.959046, 35.180652),
('Business', 'College', 31.9584, 35.183295),
('Masters', 'College', 31.958442, 35.182548),
('Law', 'College', 31.960234, 35.182993),
('Physical Studies', 'College', 31.960956, 35.181665),
('Women Studies', 'College', 31.961078, 35.183164),
('Development', 'College', 31.961162, 35.183551),
('Facility of Education', 'College', 31.960619, 35.183637),
('Nursing', 'College', 31.962129, 35.182473),
('Press', 'College', 31.961452, 35.183537),
('Second Law', 'College', 31.959542, 35.182245),
('Literature', 'College', 31.960715, 35.182686),
('Library', 'Other', 31.958837, 35.182368),
('Museum', 'Other', 31.962308, 35.183483),
('Girl Resident', 'Other', 31.960845, 35.185753),
('Theater', 'Other', 31.962082, 35.184328),
('Bop', 'Other', 31.960088, 35.182452),
('Management', 'Administration', 31.959438, 35.183146),
('Student', 'Administration', 31.959761, 35.181973),
('Money and Registration', 'Administration', 31.959043, 35.181469),
('Computer', 'Administration', 31.959676, 35.180039),
('Ritaj', 'Administration', 31.958865, 35.181907),
('West', 'Gates', 31.958557, 35.180271),
('East', 'Gates', 31.957244, 35.184965),
('North', 'Gates', 31.961765, 35.18483);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(50) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `password`) VALUES
(1200085, 'Hanna', 'hanna1234'),
(1200640, 'eyass', '123456789'),
(1201575, 'maan', 'maan123456'),
(1201681, 'eyasshal', '123456789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contactus`
--
ALTER TABLE `contactus`
  ADD PRIMARY KEY (`qid`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contactus`
--
ALTER TABLE `contactus`
  MODIFY `qid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
