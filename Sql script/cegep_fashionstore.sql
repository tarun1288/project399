-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 28, 2020 at 08:50 PM
-- Server version: 5.6.49-cll-lve
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cegep_fashionstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `fav_list`
--

CREATE TABLE `fav_list` (
  `fid` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fav_list`
--

INSERT INTO `fav_list` (`fid`, `email`, `status`, `pid`) VALUES
(5, 'testing@gmail.com', 'Unlike', 26),
(9, 'testing@gmail.com', 'Like', 24),
(10, 'testing@gmail.com', 'Like', 25),
(11, 'testing@gmail.com ', 'Like', 18),
(12, 'saitaruntangudu@gmail.com', 'Like', 26),
(13, 'saitaruntangudu@gmail.com', 'Like', 25),
(14, 'testing@gmail.com', 'Like', 21),
(15, 'testing@gmail.com', 'Like', 20),
(16, 'ravibabu89.nadakuditi@gmail.com', 'Like', 25);

-- --------------------------------------------------------

--
-- Table structure for table `table_category`
--

CREATE TABLE `table_category` (
  `cid` int(11) NOT NULL,
  `cname` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `table_category`
--

INSERT INTO `table_category` (`cid`, `cname`) VALUES
(1, 'Mens'),
(2, 'Womens'),
(3, 'Kids');

-- --------------------------------------------------------

--
-- Table structure for table `table_feedback`
--

CREATE TABLE `table_feedback` (
  `fid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `reason` varchar(500) NOT NULL,
  `rating` varchar(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `table_feedback`
--

INSERT INTO `table_feedback` (`fid`, `pid`, `name`, `email`, `reason`, `rating`) VALUES
(1, 26, 'Hoodie', 'testing@gmail.com', 'good product', '4.5'),
(2, 60, 'Women Printed Rayon Flared Kurta  (Pink)', 'testing@gmail.com ', 'good', '4.0'),
(3, 63, 'Women Printed Rayon Flared Kurta  (Pink)', 'testing@gmail.com', 'Good Quality Product', '3.0'),
(4, 69, 'Women Printed Rayon Flared Kurta  (Pink)', 'testing@gmail.com', 'GOOD Quality', '4.5'),
(5, 18, 'Striped Men Hooded Neck Red, Black T-Shirt', 'testing@gmail.com', 'NIce To shirt', '4.5'),
(6, 19, 'Women Printed Rayon Flared Kurta  (Pink)', 'testing@gmail.com ', 'Well Designed ', '3.5'),
(7, 80, 'Women Printed Rayon Flared Kurta  (Pink)', 'testing@gmail.com ', 'Well Designed ', '3.0'),
(8, 67, 'Hoodie', 'saitaruntangudu@gmail.com', 'nice\n', '3.0');

-- --------------------------------------------------------

--
-- Table structure for table `table_orders`
--

CREATE TABLE `table_orders` (
  `oid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `order_date` varchar(50) NOT NULL,
  `del_address` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `order_by_email` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `table_products`
--

CREATE TABLE `table_products` (
  `pid` int(11) NOT NULL,
  `productname` varchar(50) NOT NULL,
  `price` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  `photo` varchar(500) NOT NULL,
  `quantity` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `seller_email` varchar(100) NOT NULL,
  `available_count` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `table_products`
--

INSERT INTO `table_products` (`pid`, `productname`, `price`, `description`, `photo`, `quantity`, `cid`, `seller_email`, `available_count`) VALUES
(18, 'Striped Men Hooded Neck Red, Black T-Shirt', '299', 'Striped Men Hooded Neck Red, Black T-Shirt With Good Quality', 'http://cegepfashionstore.com/fashionstore/images/m-md3bm354white-marca-disati-original-imafvnhsqfzauyus.jpeg', 100, 1, 'paru@gmail.com ', 94),
(19, 'Women Printed Rayon Flared Kurta  (Pink)', '659', 'Women Printed Rayon Flared Kurta  (Pink) Good Looking DressSummer ware', 'http://cegepfashionstore.com/fashionstore/images/s-ss19pjs001-red-provogue-original-imafg72qm8ktqeju.jpeg', 20, 2, 'paru@gmail.com ', 8),
(20, 'Shirt', '20', 'Thanks sleeve shirt', 'http://cegepfashionstore.com/fashionstore/images/Screen Shot 2020-10-25 at 5.19.21 PM.png', 20, 1, 'paru@gmail.com', -6),
(21, 'Us Polo Jeanssss', '1502', 'Womens were', 'http://cegepfashionstore.com/fashionstore/images/clothing.jpg', 50, 2, 'paru@gmail.com', 49),
(24, 'Hoodie', '120', 'winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 20, 1, 'paru@gmail.com', 12),
(25, 'Allen Solley', '1000', 'Allen Solley Black Shirt', 'http://cegepfashionstore.com/fashionstore/images/xl-cnssts-mtrx-875-canis-original-imafheehbuquwgqe.jpeg', 20, 1, 'ravi@gmail.com ', 17),
(26, 'Nike hoodie', '120', 'winter wear', 'http://cegepfashionstore.com/fashionstore/images/brooks-leibee-27QcqVqgVg4-unsplash.jpg', 20, 1, 'rupinder@gmail.com', 19),
(27, 'blazer', '200', 'party wear', 'http://cegepfashionstore.com/fashionstore/images/adminnn.jpeg', 2, 1, 'ravi@gmail.com', 2);

-- --------------------------------------------------------

--
-- Table structure for table `table_registration`
--

CREATE TABLE `table_registration` (
  `uid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `dob` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `password` varchar(50) NOT NULL,
  `pic` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `table_registration`
--

INSERT INTO `table_registration` (`uid`, `name`, `gender`, `dob`, `email`, `phone`, `password`, `pic`, `role`) VALUES
(5, 'Testing', 'Female', '2020-11-17', 'testing@gmail.com ', '9897675432', '1234', 'http://cegepfashionstore.com/fashionstore/images/h1.jpeg', 'Customer'),
(9, 'Ravi', 'Female', '2020-11-24', 'ravi@gmail.com', '9876785432', '123', 'http://cegepfashionstore.com/fashionstore/images/clothing.jpg', 'Seller'),
(8, 'tarun', 'Male', '1997-2-1', 'saitaruntangudu@gmail.com', '5147151288', 'asdfg', 'http://cegepfashionstore.com/fashionstore/images/834305-802141-shah-rukh-khan-1.jpg', 'Customer'),
(13, 'ravi', 'Male', '2020-11-3', 'ravibabu89.nadakuditi@gmail.com', '8790604717', '123', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 'Customer'),
(10, 'rupinder', 'Female', '1992-11-24', 'rupinder@gmail.com', '8888888888', '12345', 'http://cegepfashionstore.com/fashionstore/images/brooks-leibee-27QcqVqgVg4-unsplash.jpg', 'Seller'),
(11, 'kabir', 'Male', '2008-11-6', 'kabir@gmail.com	', '123456789', '12345', 'http://cegepfashionstore.com/fashionstore/images/adminnn.jpeg', 'Customer'),
(12, 'kabir', 'Male', '2020-11-28', 'kabir@gmail.com', '123443211', '12345', 'http://cegepfashionstore.com/fashionstore/images/adminnn.jpeg', 'Seller');

-- --------------------------------------------------------

--
-- Table structure for table `tb_cart`
--

CREATE TABLE `tb_cart` (
  `pid` int(50) NOT NULL,
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` varchar(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `uname` varchar(100) NOT NULL,
  `order_id` text NOT NULL,
  `order_date` date NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cart`
--

INSERT INTO `tb_cart` (`pid`, `id`, `name`, `price`, `category`, `description`, `photo`, `quantity`, `total_price`, `uname`, `order_id`, `order_date`, `status`) VALUES
(20, 59, 'Shirt', '20', '1', 'Description  :Thanks sleeve shirt', 'http://cegepfashionstore.com/fashionstore/images/Screen Shot 2020-10-25 at 5.19.21 PM.png', 1, 20, 'testing@gmail.com', 'FS97', '2020-11-22', 'Update Order Status'),
(19, 60, 'Women Printed Rayon Flared Kurta  (Pink)', '659', '2', 'Description  :Women Printed Rayon Flared Kurta  (Pink) Good Looking DressSummer ware', 'http://cegepfashionstore.com/fashionstore/images/s-ss19pjs001-red-provogue-original-imafg72qm8ktqeju.jpeg', 1, 659, 'testing@gmail.com', 'FS67', '2020-11-22', 'Order Processing'),
(18, 61, 'Striped Men Hooded Neck Red, Black T-Shirt', '299', '1', 'Description  :Striped Men Hooded Neck Red, Black T-Shirt With Good Quality', 'http://cegepfashionstore.com/fashionstore/images/m-md3bm354white-marca-disati-original-imafvnhsqfzauyus.jpeg', 1, 299, 'testing@gmail.com', 'FS67', '2020-11-22', 'Order Processing'),
(20, 62, 'Shirt', '20', '1', 'Description  :Thanks sleeve shirt', 'http://cegepfashionstore.com/fashionstore/images/Screen Shot 2020-10-25 at 5.19.21 PM.png', 1, 20, 'testing@gmail.com', 'FS50', '2020-11-22', 'Order Processing'),
(19, 63, 'Women Printed Rayon Flared Kurta  (Pink)', '659', '2', 'Description  :Women Printed Rayon Flared Kurta  (Pink) Good Looking DressSummer ware', 'http://cegepfashionstore.com/fashionstore/images/s-ss19pjs001-red-provogue-original-imafg72qm8ktqeju.jpeg', 1, 659, 'testing@gmail.com', 'FS50', '2020-11-22', 'Order Processing'),
(18, 64, 'Striped Men Hooded Neck Red, Black T-Shirt', '299', '1', 'Description  :Striped Men Hooded Neck Red, Black T-Shirt With Good Quality', 'http://cegepfashionstore.com/fashionstore/images/m-md3bm354white-marca-disati-original-imafvnhsqfzauyus.jpeg', 1, 299, 'testing@gmail.com', 'FS50', '2020-11-22', 'Order Processing'),
(24, 65, 'Hoodie', '120', '1', 'Description  :winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 1, 120, 'ravibabu89.nadakuditi@gmail.com', 'FS92', '2020-11-24', 'Order Processing'),
(24, 66, 'Hoodie', '120', '1', 'Description  :winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 2, 240, 'ravibabu89.nadakuditi@gmail.com', 'FS56', '2020-11-24', 'Order Processing'),
(24, 67, 'Hoodie', '120', '1', 'Description  :winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 1, 120, 'saitaruntangudu@gmail.com', 'FS70', '2020-11-24', 'Order Processing'),
(24, 68, 'Hoodie', '120', '1', 'Description  :winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 1, 120, 'saitaruntangudu@gmail.com', 'FS60', '2020-11-24', 'Order Processing'),
(19, 69, 'Women Printed Rayon Flared Kurta  (Pink)', '659', '2', 'Description  :Women Printed Rayon Flared Kurta  (Pink) Good Looking DressSummer ware', 'http://cegepfashionstore.com/fashionstore/images/s-ss19pjs001-red-provogue-original-imafg72qm8ktqeju.jpeg', 1, 659, 'testing@gmail.com', 'FS67', '2020-11-24', 'Order Processing'),
(24, 70, 'Hoodie', '120', '1', 'Description  :winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 1, 120, 'testing@gmail.com', 'FS53', '2020-11-24', 'Order Processing'),
(18, 71, 'Striped Men Hooded Neck Red, Black T-Shirt', '299', '1', 'Description  :Striped Men Hooded Neck Red, Black T-Shirt With Good Quality', 'http://cegepfashionstore.com/fashionstore/images/m-md3bm354white-marca-disati-original-imafvnhsqfzauyus.jpeg', 1, 299, 'testing@gmail.com ', 'FS20', '2020-11-24', 'Order Processing'),
(20, 72, 'Shirt', '20', '1', 'Description  :Thanks sleeve shirt', 'http://cegepfashionstore.com/fashionstore/images/Screen Shot 2020-10-25 at 5.19.21 PM.png', 1, 20, 'saitaruntangudu@gmail.com', 'FS44', '2020-11-24', 'Order Processing'),
(25, 73, 'Allen Solley', '2000', '1', 'Description  :Allen Solley Black Shirt', 'http://cegepfashionstore.com/fashionstore/images/xl-cnssts-mtrx-875-canis-original-imafheehbuquwgqe.jpeg', 1, 2000, 'saitaruntangudu@gmail.com', 'FS44', '2020-11-24', 'Order Processing'),
(24, 74, 'Hoodie', '120', '1', 'Description  :winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 1, 120, 'saitaruntangudu@gmail.com', 'FS44', '2020-11-24', 'Order Processing'),
(25, 75, 'Allen Solley', '2000', '1', 'Description  :Allen Solley Black Shirt', 'http://cegepfashionstore.com/fashionstore/images/xl-cnssts-mtrx-875-canis-original-imafheehbuquwgqe.jpeg', 1, 2000, 'saitaruntangudu@gmail.com', 'FS46', '2020-11-24', 'Order Processing'),
(21, 76, 'Us Polo Jeanssss', '1502', '2', 'Description  :Womens were', 'http://cegepfashionstore.com/fashionstore/images/clothing.jpg', 1, 1502, 'saitaruntangudu@gmail.com', 'FS61', '2020-11-24', 'Order Processing'),
(25, 77, 'Allen Solley', '2000', '1', 'Description  :Allen Solley Black Shirt', 'http://cegepfashionstore.com/fashionstore/images/xl-cnssts-mtrx-875-canis-original-imafheehbuquwgqe.jpeg', 1, 2000, 'saitaruntangudu@gmail.com', 'FS15', '2020-11-24', 'Order Processing'),
(24, 78, 'Hoodie', '120', '1', 'Description  :winter Hollister hoodie', 'http://cegepfashionstore.com/fashionstore/images/h2.jpeg', 1, 120, 'saitaruntangudu@gmail.com', 'FS15', '2020-11-24', 'Order Processing'),
(26, 79, 'Nike hoodie', '120', '1', 'Description  :winter wear', 'http://cegepfashionstore.com/fashionstore/images/brooks-leibee-27QcqVqgVg4-unsplash.jpg', 1, 120, 'saitaruntangudu@gmail.com', 'FS50', '2020-11-24', 'Order Processing'),
(19, 80, 'Women Printed Rayon Flared Kurta  (Pink)', '659', '2', 'Description  :Women Printed Rayon Flared Kurta  (Pink) Good Looking DressSummer ware', 'http://cegepfashionstore.com/fashionstore/images/s-ss19pjs001-red-provogue-original-imafg72qm8ktqeju.jpeg', 2, 1318, 'testing@gmail.com ', 'FS24', '2020-11-25', 'Order Processing'),
(19, 81, 'Women Printed Rayon Flared Kurta  (Pink)', '659', '2', 'Description  :Women Printed Rayon Flared Kurta  (Pink) Good Looking DressSummer ware', 'http://cegepfashionstore.com/fashionstore/images/s-ss19pjs001-red-provogue-original-imafg72qm8ktqeju.jpeg', 2, 1318, 'testing@gmail.com ', 'FS21', '2020-11-28', 'Order Processing'),
(18, 82, 'Striped Men Hooded Neck Red, Black T-Shirt', '299', '1', 'Description  :Striped Men Hooded Neck Red, Black T-Shirt With Good Quality', 'http://cegepfashionstore.com/fashionstore/images/m-md3bm354white-marca-disati-original-imafvnhsqfzauyus.jpeg', 1, 299, 'saitaruntangudu@gmail.com', 'FS87', '2020-11-28', 'Order Processing'),
(18, 83, 'Striped Men Hooded Neck Red, Black T-Shirt', '299', '1', 'Description  :Striped Men Hooded Neck Red, Black T-Shirt With Good Quality', 'http://cegepfashionstore.com/fashionstore/images/m-md3bm354white-marca-disati-original-imafvnhsqfzauyus.jpeg', 1, 299, 'saitaruntangudu@gmail.com', 'FS62', '2020-11-28', 'Order Processing'),
(19, 84, 'Women Printed Rayon Flared Kurta  (Pink)', '659', '2', 'Description  :Women Printed Rayon Flared Kurta  (Pink) Good Looking DressSummer ware', 'http://cegepfashionstore.com/fashionstore/images/s-ss19pjs001-red-provogue-original-imafg72qm8ktqeju.jpeg', 1, 659, 'saitaruntangudu@gmail.com', 'FS7', '2020-11-28', 'Order Processing');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fav_list`
--
ALTER TABLE `fav_list`
  ADD PRIMARY KEY (`fid`),
  ADD KEY `pid` (`pid`);

--
-- Indexes for table `table_category`
--
ALTER TABLE `table_category`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `table_feedback`
--
ALTER TABLE `table_feedback`
  ADD PRIMARY KEY (`fid`);

--
-- Indexes for table `table_orders`
--
ALTER TABLE `table_orders`
  ADD PRIMARY KEY (`oid`);

--
-- Indexes for table `table_products`
--
ALTER TABLE `table_products`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `table_registration`
--
ALTER TABLE `table_registration`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `tb_cart`
--
ALTER TABLE `tb_cart`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fav_list`
--
ALTER TABLE `fav_list`
  MODIFY `fid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `table_category`
--
ALTER TABLE `table_category`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `table_feedback`
--
ALTER TABLE `table_feedback`
  MODIFY `fid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `table_orders`
--
ALTER TABLE `table_orders`
  MODIFY `oid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `table_products`
--
ALTER TABLE `table_products`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `table_registration`
--
ALTER TABLE `table_registration`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tb_cart`
--
ALTER TABLE `tb_cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fav_list`
--
ALTER TABLE `fav_list`
  ADD CONSTRAINT `fav_list_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `table_products` (`pid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
