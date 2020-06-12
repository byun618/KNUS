const express = require('express')
const router = express.Router()

module.exports = router

router.use('/user', require('./user'))
router.use('/board', require('./board'))
router.use('/chat', require('./chat'))